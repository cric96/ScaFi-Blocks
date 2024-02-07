package blockly2scafi.generators.categories.values

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generables.code.GenerableMultiInput
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class Tuple3ValueBlockType extends ValueBlockType:
  override def name: String = "tuple_3_value"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("VALUE_1", "VALUE_2", "VALUE_3")

  override def generator: Generator = GenerableMultiInput.builder
    .withInputName("VALUE_1")
    .withInputName("VALUE_2")
    .withInputName("VALUE_3")
    .withJoin(", ")
    .withPrepend("(")
    .withAppend(")")
    .build
    .generator
