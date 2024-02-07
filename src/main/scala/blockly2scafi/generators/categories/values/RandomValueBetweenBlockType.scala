package blockly2scafi.generators.categories.values

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{ Blockly, Orders }

class RandomValueBetweenBlockType extends ValueBlockType:
  override def name: String = "random_value_between"

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("MIN", "MAX")

  override def generator: Generator = (block) =>
    val min = Blockly.ScaFi.valueToCode(block, "MIN", Orders.NONE)
    val max = Blockly.ScaFi.valueToCode(block, "MAX", Orders.NONE)
    (s"${min} + Random.nextInt( (${max} - ${min}) + 1 )  ", this.order)

  override def order: Order = Orders.ORDER_ATOMIC
