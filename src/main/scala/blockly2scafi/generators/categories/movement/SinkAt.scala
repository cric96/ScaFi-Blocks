package blockly2scafi.generators.categories.movement

import blockly2scafi.{ Blockly, Orders }
import blockly2scafi.Orders.Order
import blockly2scafi.generables.code.GenerableInput
import blockly2scafi.generators.{ UnitBlockType, ValueBlockType }

class SinkAt extends ValueBlockType:
  override def name: String = "sink_at"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("LEADER")

  override def generator = (block) =>
    val leader = Blockly.ScaFi.valueToCode(block, "LEADER", Orders.NONE)
    (s"withSeparation(sinkAt($leader))(30)", order)
