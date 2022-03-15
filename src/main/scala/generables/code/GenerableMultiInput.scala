package generables.code

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable
import blockly2scafi.generators.Generable.Generator

case class GenerableMultiInput(inputNames: Seq[String], join: String, prepend: String, append: String, internalOrder: Order, externalOrder: Order) extends Generable {

  override def generator: Generator = (block) => {

    val code = this.inputNames.map(
      inputName => GenerableInput.builder
        .withInputName(inputName)
        .withExternalOrder(this.internalOrder)
        .build
        .generator
        .apply(block)._1
    )

    (this.prepend + code.mkString(this.join) + this.append, this.externalOrder)

  }

}

object GenerableMultiInput {
  def builder: GenerableMultiInputBuilder = new GenerableMultiInputBuilder
}
