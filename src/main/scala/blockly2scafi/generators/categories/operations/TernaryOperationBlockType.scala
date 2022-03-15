package blockly2scafi.generators.categories.operations

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{Block, Blockly, Orders}

class TernaryOperationBlockType extends ValueBlockType {
  override def name: String = "ternary_operation"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("CONDITION", "THEN", "ELSE")

  override def generator: Generator = (block: Block) => {
    val condition = Blockly.ScaFi.valueToCode(block, "CONDITION", Orders.NONE)
    val _then = Blockly.ScaFi.valueToCode(block, "THEN", Orders.NONE)
    val _else = Blockly.ScaFi.valueToCode(block, "ELSE", Orders.NONE)
    (s"$condition ? ${_then} : ${_else}", Orders.NONE)
  }

}
