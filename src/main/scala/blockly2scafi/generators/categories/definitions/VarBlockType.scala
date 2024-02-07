package blockly2scafi.generators.categories.definitions

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.UnitBlockType
import blockly2scafi.{ Block, Blockly, Orders }

class VarBlockType extends UnitBlockType:
  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq("NAME")

  override def inputNames: Seq[String] = Seq("VALUE")

  override def generator: Generator = (block: Block) =>
    val defName = block.getFieldValue("NAME")
    val input = Blockly.ScaFi.valueToCode(block, "VALUE", Orders.ORDER_NONE);
    (s"${name} ${defName} = ${input}", Orders.NONE)

  override def name: String = "var"
