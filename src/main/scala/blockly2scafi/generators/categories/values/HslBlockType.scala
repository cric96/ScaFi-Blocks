package blockly2scafi.generators.categories.values

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class HslBlockType extends ValueBlockType {
  override def name: String = "hsl"

  override def fieldNames: Seq[String] = Seq("VALUE_1", "VALUE_2", "VALUE_3")

  override def inputNames: Seq[String] = Seq()

  override def generator: Generator = (block) => {
    val h = block.getFieldValue("VALUE_1")
    val s = block.getFieldValue("VALUE_2")
    val l = block.getFieldValue("VALUE_3")

    (s"hsl(${h}, ${s}, ${l})", this.order)
  }

  override def order: Order = Orders.NONE
}
