package blockly2scafi.generators.categories.functions

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.UnitBlockType
import blockly2scafi.{ Block, Blockly, Orders }

class Function2ParamBlockType extends UnitBlockType:
  override def name: String = "function_2_param"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq("FUNCTION_NAME", "PARAM_1_NAME", "PARAM_2_NAME")

  override def inputNames: Seq[String] = Seq("PARAM_1_TYPE", "PARAM_2_TYPE", "BODY")

  override def generator: Generator = (block: Block) =>
    val definition = block.getFieldValue("FUNCTION_NAME")
    val param1 = block.getFieldValue("PARAM_1_NAME")
    val param1type = Blockly.ScaFi.valueToCode(block, "PARAM_1_TYPE", Orders.ORDER_NONE)
    val param2 = block.getFieldValue("PARAM_2_NAME")
    val param2type = Blockly.ScaFi.valueToCode(block, "PARAM_2_TYPE", Orders.ORDER_NONE)
    val code = Blockly.ScaFi.statementToCode(block, "BODY")
    (s"def ${definition}(${param1}: ${param1type}, ${param2}: ${param2type}) = {\n${code}\n}", Orders.NONE)
