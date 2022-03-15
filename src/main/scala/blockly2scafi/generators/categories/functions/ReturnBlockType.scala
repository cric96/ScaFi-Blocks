package blockly2scafi.generators.categories.functions

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generables.code.GenerableInput
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.UnitBlockType

class ReturnBlockType extends UnitBlockType {
  override def name: String = "return"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("RETURN")

  override def generator: Generator = GenerableInput.builder
    .withInputName("RETURN")
    .build
    .generator
}
