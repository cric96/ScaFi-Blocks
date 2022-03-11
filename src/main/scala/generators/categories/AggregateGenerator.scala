package generators.categories

import blockly2scafi.{Block, Blockly, Orders}
import extractors.Extractable
import extractors.Extractable.Extractor
import extractors.code.{CodeExtractor, CodesExtractor}
import generators.Generator

object AggregateGenerator extends Generator {

  private val aggregateProgramGenerator = new Extractable {

    override def getExtractor: Extractor = (block: Block) => {
      val standardImportMap = Map(
        "random_value" -> Seq("scala.util.Random"),
        "random_value_between" -> Seq("scala.util.Random")
      )

      val scafiImportMap = Map(
        "distance_to" -> Seq("StandardSensors", "BlockG"),
        "distance_between" -> Seq("StandardSensors", "BlockG"),
        "channel" -> Seq("StandardSensors", "BlockG"),
        "distance_between" -> Seq("Actuation")
      )

      var standardImportArray = Seq[String]()
      var scafiImportArray = Seq[String]()

      val workspace = block.workspace;
      val allBlocks = workspace.getAllBlocks()

      allBlocks.foreach(_block => {
        if (standardImportMap.contains(_block.`type`)) {
          val modules = standardImportMap(_block.`type`)
          modules.foreach(module => {
            if (!standardImportArray.contains(module)) {
              standardImportArray = standardImportArray :+ module
            }
          })
        }

        if (scafiImportMap.contains(_block.`type`)){
          val modules = scafiImportMap(_block.`type`)
          modules.foreach(module => {
            if (!scafiImportArray.contains(module)){
              scafiImportArray = scafiImportArray :+ module
            }
          })
        }
      })

      val standardImportCode = standardImportArray.map(module => "import " + module + ";").mkString("\n") + "\n";
      var scafiImportCode = "";

      if (scafiImportArray.nonEmpty) {
        scafiImportCode = "//using " + scafiImportArray.mkString(", ") + "\n";
      }

      val otherCode = Blockly.ScaFi.blockToCode(block.getInputTargetBlock("AGGREGATE_PROGRAM_MAIN"), false)
      //Not using statementToCode to avoid first INDENT

      (standardImportCode + scafiImportCode + otherCode, Orders.NONE)
    }
  }
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
  override protected def directCodeGenerators: Map[String, Extractable] = Map(
    "aggregate_program" -> aggregateProgramGenerator
  )
}
