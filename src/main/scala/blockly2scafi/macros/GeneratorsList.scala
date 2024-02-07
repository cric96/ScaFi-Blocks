package blockly2scafi.macros
import scala.util.{Try => Verify}
import blockly2scafi.generators.{UnitBlockType, ValueBlockType}
import scala.quoted.*
object GeneratorsList:
  //inline def unitBlocks(): Seq[UnitBlockType] = ${unitBlocksImplementation}
  
  transparent inline def searchByType[T](inline path: String): List[T] =
    ${ searchByTypeMacro[T]('path) }

  def searchByTypeMacro[T : Type](pathExpr: Expr[String])(using quotes: Quotes): Expr[List[T]] =
    import quotes.reflect.*

    val packageName = pathExpr.value match {
      case Some(str) => str
      case None => report.errorAndAbort("Package name is not visible at compiletime")
    }

    def isClassEligible(classDef: ClassDef): Boolean =
      val isNotAClassModule = classDef.symbol.moduleClass != classDef.symbol
      val isTheRightType = classDef.symbol.typeRef <:< TypeRepr.of[T]
      isNotAClassModule && isTheRightType

    val symbol = Symbol.requiredPackage(packageName)
    if(symbol == Symbol.noSymbol) {
      report.errorAndAbort(s"Package by the name of ${packageName} could not be found")
    }
    
    val news = recursiveClassDef(symbol).filter(isClassEligible).map(
      clazz => Apply(
        Select(New(TypeIdent(clazz.symbol)), clazz.constructor.symbol),
        List.empty)
    )

    val expressions = news.map(_.asExprOf[T])
    Expr.ofList(news.map(_.asExprOf[T]))
  
  def recursiveClassDef(using q: Quotes)(symbol: q.reflect.Symbol): List[q.reflect.ClassDef] = {
    import q.reflect.*
    symbol.declarations.collect {
      case obj if obj.isClassDef =>
        val verified = Verify(obj.tree.asInstanceOf[ClassDef]) 
        verified.map(List(_)).getOrElse(List.empty)
      case obj if obj.isPackageDef => recursiveClassDef(obj)
    }.flatten
  }
  def searchSymbolForDeclaration(using q: Quotes)(symbol: q.reflect.Symbol, test: q.reflect.ValDef => Boolean): Seq[q.reflect.ValDef] =
    import q.reflect.*

    try {
      symbol.declarations.collect {
        case obj if obj.fullName.contains("#") =>
          Seq.empty
        case pkg if pkg.isPackageDef =>
          searchSymbolForDeclaration(pkg, test)
        case obj if obj.isValDef &&
          symbol.isValDef &&
          !symbol.isPackageDef &&
          obj.tree.asInstanceOf[ValDef].tpt.tpe =:= symbol.tree.asInstanceOf[ValDef].tpt.tpe =>
          Seq.empty
        case obj if obj.isValDef && test(obj.tree.asInstanceOf[ValDef]) =>
          Seq(obj.tree.asInstanceOf[ValDef])
        case obj if obj.isValDef =>
          searchSymbolForDeclaration(obj, test)
        case _ =>
          Seq.empty
      }.flatten
    } catch {
      case e: Throwable =>
        Seq.empty
    }


