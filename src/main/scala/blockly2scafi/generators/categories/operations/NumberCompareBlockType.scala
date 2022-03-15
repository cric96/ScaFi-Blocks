package blockly2scafi.generators.categories.operations

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{Block, Blockly, Orders}

class NumberCompareBlockType extends ValueBlockType {
  override def name: String = "number_compare"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq("OPERATOR")

  override def inputNames: Seq[String] = Seq("FIRST", "SECOND")

  override def generator: Generator = (block: Block) => {
    val operation = block.getFieldValue("OPERATOR")

    var operator: String = ""
    var order: Order = Orders.NONE
    if (operation == "GREATER") {
      operator = " > "
      order = Orders.ORDER_RELATIONAL
    } else if (operation == "GREATER_OR_EQUAL") {
      operator = " >= "
      order = Orders.ORDER_RELATIONAL
    } else if (operation == "EQUAL") {
      operator = " == "
      order = Orders.ORDER_EQUALITY
    } else if (operation == "NOT_EQUAL") {
      operator = " != "
      order = Orders.ORDER_EQUALITY
    } else if (operation == "LESS_OR_EQUAL") {
      operator = " <= "
      order = Orders.ORDER_RELATIONAL
    } else { //LESS
      operator = " < "
      order = Orders.ORDER_RELATIONAL
    }
    val first = Blockly.ScaFi.valueToCode(block, "FIRST", order)
    val second = Blockly.ScaFi.valueToCode(block, "SECOND", order)
    val code = first + operator + second

    (code, order)
  }

}
