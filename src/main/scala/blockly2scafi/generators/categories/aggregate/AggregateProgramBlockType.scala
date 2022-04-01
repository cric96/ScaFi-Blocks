package blockly2scafi.generators.categories.aggregate

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.UnitBlockType
import blockly2scafi.{Block, Blockly, Orders}

class AggregateProgramBlockType extends UnitBlockType {
  private val standardImportMap = Map(
    "random_value" -> Seq("scala.util.Random"),
    "random_value_between" -> Seq("scala.util.Random")
  )
  private val scafiImportMap = Map(
    "distance_to" -> Seq("StandardSensors", "BlockG"),
    "distance_between" -> Seq("StandardSensors", "BlockG"),
    "channel" -> Seq("StandardSensors", "BlockG"),
    "distance_between" -> Seq("Actuation")
  )

  override def name: String = "aggregate_program"

  override def fieldNames: Seq[String] = Seq()

  override def generator: Generator = (block: Block) => {

    val standardImportSet = block.workspace.getAllBlocks()
      .filter {
        standardImportMap contains _.`type`
      }
      .map {
        standardImportMap get _.`type`
      }
      .map {
        _.get
      }
      .flatten
      .toSet

    val scafiImportSet = block.workspace.getAllBlocks()
      .filter {
        scafiImportMap contains _.`type`
      }
      .map {
        scafiImportMap get _.`type`
      }
      .map {
        _.get
      }
      .flatten
      .toSet

    val standardImportCode = standardImportSet
      .map {
        "import " + _ + ";"
      }
      .mkString("\n") + "\n"

    val scafiImportCode = if (scafiImportSet.nonEmpty) "//using " + scafiImportSet.mkString(", ") + "\n" else ""

    //Not using statementToCode to avoid first INDENT
    val otherCode = Blockly.ScaFi.blockToCode(block.getInputTargetBlock(this.inputNames.head), flag = false)

    (standardImportCode + scafiImportCode + otherCode, this.order)
  }

  override def order: Order = Orders.NONE

  override def inputNames: Seq[String] = Seq("AGGREGATE_PROGRAM_MAIN")

}
