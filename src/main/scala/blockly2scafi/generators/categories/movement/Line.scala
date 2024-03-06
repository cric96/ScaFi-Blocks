package blockly2scafi.generators.categories.movement

import blockly2scafi.{ Blockly, Orders }
import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class Line extends ValueBlockType:

  override def name: String = "line"

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("LEADER")

  override def generator: Generator = (block) =>
    (s"line(${Blockly.ScaFi.valueToCode(block, "LEADER", Orders.NONE)}, 5, 2)", order)

  override def order: Order = Orders.ORDER_ATOMIC
