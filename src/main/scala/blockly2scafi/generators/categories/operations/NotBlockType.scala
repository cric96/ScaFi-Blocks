package blockly2scafi.generators.categories.operations

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generables.code.GenerableInput
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class NotBlockType extends ValueBlockType:
  override def name: String = "not"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("NAME")

  override def generator: Generator = GenerableInput.builder
    .withInputName("NAME")
    .withPrepend("!")
    .build
    .generator
