package blockly2scafi.generators.categories.functions

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{ Block, Blockly, Orders }

class CallFunction1ParamBlockType extends ValueBlockType:
  override def name: String = "call_function_1_param"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq("NAME")

  override def inputNames: Seq[String] = Seq("PARAM")

  override def generator: Generator = (block: Block) =>
    val name = block.getFieldValue("NAME");
    val param = Blockly.ScaFi.valueToCode(block, "PARAM", Orders.ORDER_NONE);
    (s"${name}(${param})", Orders.ORDER_FUNCTION_CALL);
