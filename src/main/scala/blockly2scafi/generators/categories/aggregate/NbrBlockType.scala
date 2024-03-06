package blockly2scafi.generators.categories.aggregate

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generables.code.GenerableInput
import blockly2scafi.generators.ValueBlockType

class NbrBlockType extends ValueBlockType:
  override def name: String = "nbr"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("EXPRESSION")

  override def generator = GenerableInput.builder
    .withInputName("EXPRESSION")
    .withPrepend("nbr { ")
    .withAppend(" }")
    .build
    .generator
