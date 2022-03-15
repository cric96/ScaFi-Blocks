package blockly2scafi.generators.categories.operations

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{Block, Blockly, Orders}

class NumberOperationBlockType extends ValueBlockType {
  override def name: String = "number_operation"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq("OPERATOR")

  override def inputNames: Seq[String] = Seq("FIRST", "SECOND")

  override def generator: Generator = (block: Block) => {
    val operation = block.getFieldValue("OPERATOR")

    var order: Order = Orders.NONE
    var operator: String = ""
    if (operation == "ADDITION") {
      operator = " + "
      order = Orders.ORDER_ADDITION
    } else if (operation == "SUBTRACTION") {
      operator = " - "
      order = Orders.ORDER_SUBTRACTION
    } else if (operation == "MULTIPLICATION") {
      operator = " * "
      order = Orders.ORDER_MULTIPLICATION
    } else if (operation == "DIVISION") {
      operator = " / "
      order = Orders.ORDER_DIVISION
    } else { //MODULUS
      operator = " % "
      order = Orders.ORDER_MODULUS
    }
    val first = Blockly.ScaFi.valueToCode(block, "FIRST", order)
    val second = Blockly.ScaFi.valueToCode(block, "SECOND", order)
    val code = first + operator + second
    (code, order)
  }

}
