package blockly2scafi.generators.categories.functions

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{ Block, Blockly, Orders }

class Lambda2ParamBlockType extends ValueBlockType:
  override def name: String = "lambda_2_param"

  override def fieldNames: Seq[String] = Seq("PARAM_1_NAME", "PARAM_2_NAME")

  override def inputNames: Seq[String] = Seq("FUNCTION")

  override def generator: Generator = (block: Block) =>
    val param1 = block.getFieldValue("PARAM_1_NAME")
    val param2 = block.getFieldValue("PARAM_2_NAME")
    val call = Blockly.ScaFi.valueToCode(block, "FUNCTION", this.order)

    (s"(${param1}, ${param2}) => ${call}", Orders.NONE)

  override def order: Order = Orders.NONE
