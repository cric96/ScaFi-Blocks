package blockly2scafi.generables.code

import blockly2scafi.Orders.Order
import blockly2scafi.generables.AbstractGenerableBuilder
import blockly2scafi.generators.Generable

class GenerableInputBuilder extends AbstractGenerableBuilder {

  protected var internalOrder: Order = 1;
  protected var externalOrder: Order = 1;
  private var inputName: String = "";

  def withInputName(inputName: String): this.type = {
    this.inputName = inputName;
    this
  }

  def withInternalOrder(internalOrder: Order): this.type = {
    this.internalOrder = internalOrder;
    this
  }

  def withExternalOrder(externalOrder: Order): this.type = {
    this.externalOrder = externalOrder;
    this
  }

  override def build: Generable = new GenerableInput(this.inputName, this.prepend, this.append, this.internalOrder, this.externalOrder)
}
