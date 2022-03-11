package generators.categories

import blockly2scafi.{Block, Orders}
import extractors.Extractable
import extractors.Extractable.Extractor
import extractors.code.{CodeExtractor, CodesExtractor}
import generators.Generator

object AggregateGenerator extends Generator {

  private val nbrGenerator = CodeExtractor.builder
    .withInputName("EXPRESSION")
    .withPrepend("nbr { ")
    .withAppend(" }")
    .build

  private val _conditionExtractor = (name: String) => CodeExtractor.builder
    .withInputName("CONDITION")
    .withPrepend(s"${name}(")
    .withAppend(") {\n  ")
    .build
  private val _branchesExtractor = CodesExtractor.builder
    .withInputName("FIRST_BRANCH")
    .withInputName("SECOND_BRANCH")
    .withJoin("\n}{\n  ")
    .withAppend("\n}")
    .build
  private val muxAndBranchGenerator = (name: String) => new Extractable {
    override def getExtractor: Extractor = (block: Block) => {
      val condition = _conditionExtractor(name).getExtractor.apply(block)
      val branches = _branchesExtractor.getExtractor.apply(block)
      (condition._1 + branches._1, Orders.NONE)
    }
  }
  private val muxGenerator = muxAndBranchGenerator("mux")
  private val branchGenerator = muxAndBranchGenerator("branch")

  override protected def codeTupleGenerators: Map[String, Extractable] = Map(
    "nbr" -> nbrGenerator,
    "mux" -> muxGenerator,
    "branch" -> branchGenerator
  )
  override protected def directCodeGenerators: Map[String, Extractable] = Map()
}
