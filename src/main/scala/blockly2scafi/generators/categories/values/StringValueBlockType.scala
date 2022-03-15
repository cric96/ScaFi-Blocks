package blockly2scafi.generators.categories.values

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generables.field.GenerableField
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class StringValueBlockType extends ValueBlockType {
  override def name: String = "string_value"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq("VALUE")

  override def inputNames: Seq[String] = Seq()

  override def generator: Generator = GenerableField.builder
    .withFieldName("VALUE")
    .withPrepend("\"")
    .withAppend("\"")
    .build
    .generator
}
