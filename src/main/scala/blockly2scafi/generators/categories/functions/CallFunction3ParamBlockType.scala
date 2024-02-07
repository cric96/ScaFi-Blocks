package blockly2scafi.generators.categories.functions

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{ Block, Blockly, Orders }

class CallFunction3ParamBlockType extends ValueBlockType:
  override def name: String = "call_function_3_param"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq("NAME")

  override def inputNames: Seq[String] = Seq("PARAM_1", "PARAM_2", "PARAM_3")

  override def generator: Generator = (block: Block) =>
    val name = block.getFieldValue("NAME");
    val param = Blockly.ScaFi.valueToCode(block, "PARAM_1", Orders.ORDER_NONE);
    val param2 = Blockly.ScaFi.valueToCode(block, "PARAM_2", Orders.ORDER_NONE);
    val param3 = Blockly.ScaFi.valueToCode(block, "PARAM_3", Orders.ORDER_NONE);
    (s"${name}(${param}, ${param2}, ${param3})", Orders.ORDER_FUNCTION_CALL);
