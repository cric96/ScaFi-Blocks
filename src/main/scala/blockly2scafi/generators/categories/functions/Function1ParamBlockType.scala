package blockly2scafi.generators.categories.functions

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.UnitBlockType
import blockly2scafi.{Block, Blockly, Orders}

class Function1ParamBlockType extends UnitBlockType {
  override def name: String = "function_1_param"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq("FUNCTION_NAME", "PARAM_NAME")

  override def inputNames: Seq[String] = Seq("PARAM_TYPE", "BODY")

  override def generator: Generator = (block: Block) => {
    val definition = block.getFieldValue("FUNCTION_NAME")
    val param = block.getFieldValue("PARAM_NAME")
    val _type = Blockly.ScaFi.valueToCode(block, "PARAM_TYPE", Orders.ORDER_NONE)
    val code = Blockly.ScaFi.statementToCode(block, "BODY")
    (s"def ${definition}(${param}: ${_type}):\n${code}\n", Orders.NONE)
  }
}
