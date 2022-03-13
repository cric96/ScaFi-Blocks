package generables.code

import blockly2scafi.Blockly
import blockly2scafi.Orders.Order
import generables.Generable
import generables.Generable.Generator

case class GenerableInput(inputName: String, prepend: String, append: String, internalOrder: Order, externalOrder: Order) extends Generable {

  override def generator: Generator = (block) =>
    (
      this.prepend
      + Blockly.ScaFi.valueToCode(block, this.inputName, this.internalOrder)
      + this.append,
      this.externalOrder
    )
}

object GenerableInput {
  def builder: GenerableInputBuilder = new GenerableInputBuilder()
}