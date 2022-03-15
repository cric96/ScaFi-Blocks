package blockly2scafi.generables.code

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.{Block, Blockly}

case class GenerableInput(inputName: String, prepend: String, append: String, internalOrder: Order, externalOrder: Order) extends Generable {

  override def generator: Generator = (block: Block) =>
    (this.prepend
      + Blockly.ScaFi.valueToCode(block, this.inputName, this.internalOrder)
      + this.append,
      this.externalOrder)
}

object GenerableInput {
  def builder: GenerableInputBuilder = new GenerableInputBuilder()
}