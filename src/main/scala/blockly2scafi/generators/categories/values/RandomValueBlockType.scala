package blockly2scafi.generators.categories.values

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generables.code.GenerableInput
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class RandomValueBlockType extends ValueBlockType {
  override def name: String = "random_value"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("MAX")

  override def generator: Generator = GenerableInput.builder
    .withInputName("MAX")
    .withPrepend("Random.nextInt(")
    .withAppend(")")
    .build
    .generator
}
