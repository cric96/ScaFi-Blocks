package blockly2scafi.generators.categories.types

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generables.field.GenerableField
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class BooleanClassBlockType extends ValueBlockType:
  override def name: String = "class_boolean"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq("NAME")

  override def inputNames: Seq[String] = Seq()

  override def generator: Generator = GenerableField.builder
    .withFieldName("NAME")
    .build
    .generator
