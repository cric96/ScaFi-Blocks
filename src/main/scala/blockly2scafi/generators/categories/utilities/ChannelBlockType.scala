package blockly2scafi.generators.categories.utilities

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.UnitBlockType
import generables.code.GenerableMultiInput

class ChannelBlockType extends UnitBlockType {
  override def name: String = "channel"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("SOURCE", "TARGET", "WIDTH")

  override def generator: Generator = GenerableMultiInput.builder
    .withInputName("SOURCE")
    .withInputName("TARGET")
    .withInputName("WIDTH")
    .withPrepend("channel(")
    .withAppend(")")
    .withJoin(", ")
    .build
    .generator

}
