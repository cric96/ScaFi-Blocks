package blockly2scafi.generators.categories.operations

import blockly2scafi.Orders.Order
import blockly2scafi.generables.code.GenerableMultiInput
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{Block, Orders}

class BooleanOperationBlockType extends ValueBlockType {
  override def name: String = "boolean_operation"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq("OPERATOR")

  override def inputNames: Seq[String] = Seq("LEFT", "RIGHT")

  override def generator: Generator = (block: Block) => GenerableMultiInput.builder
    .withInputName("LEFT")
    .withInputName("RIGHT")
    .withJoin(" " + block.getFieldValue("OPERATOR") + " ")
    .build
    .generator
    .apply(block)

}
