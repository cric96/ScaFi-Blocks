package blockly2scafi.generators.categories.operations

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generables.code.GenerableMultiInput
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class EqualsBlockType extends ValueBlockType:
  override def name: String = "equals"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("LEFT", "RIGHT")

  override def generator: Generator = GenerableMultiInput.builder
    .withInputName("LEFT")
    .withInputName("RIGHT")
    .withJoin(" == ")
    .build
    .generator
