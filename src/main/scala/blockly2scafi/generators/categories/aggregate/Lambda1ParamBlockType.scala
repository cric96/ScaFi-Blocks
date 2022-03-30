package blockly2scafi.generators.categories.aggregate

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{Block, Blockly, Orders}

class Lambda1ParamBlockType extends ValueBlockType {
  override def name: String = "lambda_1_param"

  override def fieldNames: Seq[String] = Seq("PARAM_NAME")

  override def inputNames: Seq[String] = Seq("FUNCTION")

  override def generator: Generator = (block: Block) => {
    val param = block.getFieldValue("PARAM_NAME")
    val call = Blockly.ScaFi.valueToCode(block, "FUNCTION", this.order)

    (s"(${param}) => ${call}", Orders.NONE)
  }

  override def order: Order = Orders.ORDER_ATOMIC
}
