package generables.code

import blockly2scafi.Orders.Order
import generables.{AbstractGenerableBuilder, Generable}

class GenerableInputBuilder extends AbstractGenerableBuilder {

  private var inputName: String = "";
  protected var internalOrder: Order = 1;
  protected var externalOrder: Order = 1;

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
