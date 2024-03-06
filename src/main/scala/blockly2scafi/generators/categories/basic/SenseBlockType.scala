package blockly2scafi.generators.categories.basic

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{Blockly, Orders}

class SenseBlockType extends ValueBlockType:
  override def name: String = "sense"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("TYPE", "SENSOR_NAME")

  override def generator: Generator = (block) =>
    val _type = Blockly.ScaFi.valueToCode(block, "TYPE", Orders.NONE)
    val name = Blockly.ScaFi.valueToCode(block, "SENSOR_NAME", Orders.NONE)
    val code = s"sense[${_type}](${name})"
    (code, Orders.NONE)
