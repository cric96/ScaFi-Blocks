package generators.categories

import blockly2scafi.{Block, Blockly, Orders}
import extractors.Extractable
import extractors.Extractable.{Extractor, Order}
import extractors.code.CodesExtractor
import generators.Generator

object OperatorsGenerator extends Generator {

  private val equalsGenerator = CodesExtractor.builder
    .withInputName("LEFT")
    .withInputName("RIGHT")
    .withJoin(" == ")
    .build

  private val booleanOperationGenerator = new Extractable {
    override def getExtractor: Extractor = (block: Block) => CodesExtractor.builder
      .withInputName("LEFT")
      .withInputName("RIGHT")
      .withJoin(" " + block.getFieldValue("OPERATOR") + " ")
      .build
      .getExtractor
      .apply(block)
  }

  private val ternaryOperationGenerator = new Extractable {
    override def getExtractor: Extractor = (block: Block) => {
      val condition = Blockly.ScaFi.valueToCode(block, "CONDITION", Orders.NONE);
      val _then = Blockly.ScaFi.valueToCode(block, "THEN", Orders.NONE);
      val _else = Blockly.ScaFi.valueToCode(block, "ELSE", Orders.NONE);
      (s"${condition} ? ${_then} : ${_else}", Orders.NONE);
    }
  }

  private val numberComparatorGenerator = new Extractable {
    override def getExtractor: Extractor = (block: Block) => {
      val operation = block.getFieldValue("OPERATOR");

      var operator: String = ""
      var order: Order = Orders.NONE
      if (operation == "GREATER") {
        operator = " > ";
        order = Orders.ORDER_RELATIONAL;
      } else if (operation == "GREATER_OR_EQUAL") {
        operator = " >= ";
        order = Orders.ORDER_RELATIONAL;
      } else if (operation == "EQUAL") {
        operator = " == ";
        order = Orders.ORDER_EQUALITY;
      } else if (operation == "NOT_EQUAL") {
        operator = " != ";
        order = Orders.ORDER_EQUALITY;
      } else if (operation == "LESS_OR_EQUAL") {
        operator = " <= ";
        order = Orders.ORDER_RELATIONAL;
      } else { //LESS
        operator = " < ";
        order = Orders.ORDER_RELATIONAL;
      }
      val first = Blockly.ScaFi.valueToCode(block, "FIRST", order);
      val second = Blockly.ScaFi.valueToCode(block, "SECOND", order);
      val code = first + operator + second;

      (code, order);
    }
  }

  private val numberOperationGenerator = new Extractable {
    override def getExtractor: Extractor = (block: Block) => {
      val operation = block.getFieldValue("OPERATOR");

      var order: Order = Orders.NONE
      var operator: String = ""
      if (operation == "ADDITION") {
        operator = " + "
        order = Orders.ORDER_ADDITION
      } else if (operation == "SUBTRACTION") {
        operator = " - "
        order = Orders.ORDER_SUBTRACTION;
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
      val code = first + operator + second;
      (code, order);
    }
  }

  override protected def codeTupleGenerators: Map[String, Extractable] = Map(
    "equals" -> equalsGenerator,
    "boolean_operation" -> booleanOperationGenerator,
    "ternary_operation" -> ternaryOperationGenerator,
    "number_compare" -> numberComparatorGenerator,
    "number_operation" -> numberOperationGenerator
  )

  override protected def directCodeGenerators: Map[String, Extractable] = Map()
}
