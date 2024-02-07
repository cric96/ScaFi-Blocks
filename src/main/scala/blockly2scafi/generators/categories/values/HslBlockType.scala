package blockly2scafi.generators.categories.values

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{ Blockly, Orders }

class HslBlockType extends ValueBlockType:
  override def name: String = "hsl"

  override def fieldNames: Seq[String] = Seq("VALUE_1", "VALUE_2", "VALUE_3")

  override def inputNames: Seq[String] = Seq()

  override def generator: Generator = (block) =>
    val h = Blockly.ScaFi.valueToCode(block, "VALUE_1", this.order)
    val s = Blockly.ScaFi.valueToCode(block, "VALUE_2", this.order)
    val l = Blockly.ScaFi.valueToCode(block, "VALUE_3", this.order)

    (s"hsl(${h}, ${s}, ${l})", this.order)

  override def order: Order = Orders.NONE
