package blockly2scafi.macros
import scala.quoted.*
import scala.util.Try as Verify
object GeneratorsList:
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
        List.empty
      )
    )

    val expressions = news.map(_.asExprOf[T])
    Expr.ofList(news.map(_.asExprOf[T]))
  
  def recursiveClassDef(using q: Quotes)(symbol: q.reflect.Symbol): List[q.reflect.ClassDef] =
    import q.reflect.*
    symbol.declarations.collect {
      case obj if obj.isClassDef =>
        Verify(obj.tree.asInstanceOf[ClassDef]).map(List(_)).getOrElse(List.empty)
      case obj if obj.isPackageDef => recursiveClassDef(obj)
    }.flatten

