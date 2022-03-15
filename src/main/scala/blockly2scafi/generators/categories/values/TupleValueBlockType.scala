package blockly2scafi.generators.categories.values

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generables.code.GenerableMultiInput
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class TupleValueBlockType extends ValueBlockType {
  override def name: String = "tuple_value"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("VALUE_1", "VALUE_2")

  override def generator: Generator = GenerableMultiInput.builder
    .withInputName("VALUE_1")
    .withInputName("VALUE_2")
    .withJoin(", ")
    .withPrepend("(")
    .withAppend(")")
    .build
    .generator
}
