package blockly2scafi.generators.categories.basic

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generables.code.GenerableInput
import blockly2scafi.generators.UnitBlockType

class OutputBlockType extends UnitBlockType:
  override def name: String = "output"

  override def order: Order = Orders.NONE

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("OUTPUT_VALUE")

  override def generator = GenerableInput.builder
    .withInputName("OUTPUT_VALUE")
    .build
    .generator
