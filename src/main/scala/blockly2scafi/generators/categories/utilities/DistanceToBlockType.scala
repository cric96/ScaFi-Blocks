package blockly2scafi.generators.categories.utilities

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generables.code.GenerableInput
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class DistanceToBlockType extends ValueBlockType {
  override def name: String = "distance_to"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("SRC")

  override def generator: Generator = GenerableInput.builder
    .withInputName("SRC")
    .withPrepend("distanceTo(")
    .withAppend(")")
    .build
    .generator

}
