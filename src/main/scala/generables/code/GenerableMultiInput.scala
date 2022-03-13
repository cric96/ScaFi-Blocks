package generables.code

import blockly2scafi.Blockly
import blockly2scafi.Orders.Order
import generables.Generable
import generables.Generable.Generator

case class GenerableMultiInput(inputNames: Seq[String], join: String, prepend: String, append: String, internalOrder: Order, externalOrder: Order) extends Generable {

  override def generator: Generator = (block) => {

    val code = this.inputNames.map(
      inputName => GenerableInput.builder
        .withInputName(inputName)
        .withExternalOrder(this.internalOrder)
        .build
        .generator(block)._1
    )

    (this.prepend + code.mkString(this.join) + this.append, this.externalOrder)

  }

}

object GenerableMultiInput {
  def builder: GenerableMultiInputBuilder = new GenerableMultiInputBuilder
}
