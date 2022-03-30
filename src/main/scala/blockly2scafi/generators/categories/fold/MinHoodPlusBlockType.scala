package blockly2scafi.generators.categories.fold

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{Blockly, Orders}

class MinHoodPlusBlockType extends ValueBlockType {
  override def name: String = "minhoodplus"

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("EXPRESSION")

  override def generator: Generator = (block) => {
    val expression = Blockly.ScaFi.valueToCode(block, "EXPRESSION", this.order)
    (s"minHoodPlus { ${expression} }", Orders.NONE)
  }

  override def order: Order = Orders.ORDER_ATOMIC


}
