package blockly2scafi.generators.categories.movement

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{ Blockly, Orders }

class CenteredCircle extends ValueBlockType:

  override def name: String = "centered_circle"

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("LEADER")

  override def generator: Generator = (block) =>
    (s"centeredCircle(${Blockly.ScaFi.valueToCode(block, "LEADER", Orders.NONE)}, 60, 2)", order)

  override def order: Order = Orders.ORDER_ATOMIC
