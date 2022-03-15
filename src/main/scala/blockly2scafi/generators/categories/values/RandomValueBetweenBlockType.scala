package blockly2scafi.generators.categories.values

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generables.code.GenerableMultiInput
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class RandomValueBetweenBlockType extends ValueBlockType {
  override def name: String = "random_value_between"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("MIN", "MAX")

  override def generator: Generator = GenerableMultiInput.builder
    .withInputName("MIN")
    .withInputName("MAX")
    .withJoin(", ")
    .withPrepend("Random.between(")
    .withAppend(")")
    .build
    .generator
}
