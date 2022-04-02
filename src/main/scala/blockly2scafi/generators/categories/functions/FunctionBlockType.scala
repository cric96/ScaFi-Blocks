package blockly2scafi.generators.categories.functions

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.UnitBlockType
import blockly2scafi.{Block, Blockly, Orders}

class FunctionBlockType extends UnitBlockType {
  override def name: String = "function"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq("FUNCTION_NAME")

  override def inputNames: Seq[String] = Seq("BODY")

  override def generator: Generator = (block: Block) => {
    val definition = block.getFieldValue("FUNCTION_NAME");
    val code = Blockly.ScaFi.statementToCode(block, "BODY");
    (s"def ${definition} = {\n${code}\n}", Orders.NONE)
  }
}
