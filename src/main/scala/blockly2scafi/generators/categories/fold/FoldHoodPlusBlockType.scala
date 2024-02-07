package blockly2scafi.generators.categories.fold

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{ Blockly, Orders }

class FoldHoodPlusBlockType extends ValueBlockType:
  override def name: String = "foldhoodplus"

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("START", "AGGREGATOR", "EXPRESSION")

  override def generator: Generator = (block) =>

    val start = Blockly.ScaFi.valueToCode(block, "START", this.order)
    val aggregator = Blockly.ScaFi.valueToCode(block, "AGGREGATOR", this.order)
    val expression = Blockly.ScaFi.valueToCode(block, "EXPRESSION", this.order)

    (s"foldhoodPlus(${start})(${aggregator}) { ${expression} }", Orders.NONE)

  override def order: Order = Orders.NONE
