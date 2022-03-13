package generators.categories

import blockly2scafi.Orders.Order
import blockly2scafi.{Block, Blockly, Orders}
import generables.Generable
import generables.Generable.Generator
import generables.code.GenerableMultiInput
import generators.CategoryBlocksGenerators

object Operators extends CategoryBlocksGenerators {

  private val equals = GenerableMultiInput.builder
    .withInputName("LEFT")
    .withInputName("RIGHT")
    .withJoin(" == ")
    .build

  private val booleanOperation = new Generable {
    override def generator: Generator = (block: Block) => GenerableMultiInput.builder
      .withInputName("LEFT")
      .withInputName("RIGHT")
      .withJoin(" " + block.getFieldValue("OPERATOR") + " ")
      .build
      .generator
      .apply(block)
  }

  private val ternaryOperation = new Generable {
    override def generator: Generator = (block: Block) => {
      val condition = Blockly.ScaFi.valueToCode(block, "CONDITION", Orders.NONE)
      val _then = Blockly.ScaFi.valueToCode(block, "THEN", Orders.NONE)
      val _else = Blockly.ScaFi.valueToCode(block, "ELSE", Orders.NONE)
      (s"$condition ? ${_then} : ${_else}", Orders.NONE)
    }
  }

  private val numberComparator = new Generable {
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

  private val numberOperation = new Generable {
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

  override protected def generableValueBlocks: Map[String, Generable] = Map(
    "equals" -> equals,
    "boolean_operation" -> booleanOperation,
    "ternary_operation" -> ternaryOperation,
    "number_compare" -> numberComparator,
    "number_operation" -> numberOperation
  )

  override protected def generableUnitBlocks: Map[String, Generable] = Map()
}
