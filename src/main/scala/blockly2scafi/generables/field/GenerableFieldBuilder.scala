package blockly2scafi.generables.field

import blockly2scafi.Orders.Order
import blockly2scafi.generables.AbstractGenerableBuilder
import blockly2scafi.generators.Generable

class GenerableFieldBuilder extends AbstractGenerableBuilder:

  private var fieldName: String = "";
  private var order: Order = 0;

  def withFieldName(fieldName: String): GenerableFieldBuilder =
    this.fieldName = fieldName;
    this

  def withOrder(order: Order): GenerableFieldBuilder =
    this.order = order;
    this

  override def build: Generable = new GenerableField(this.fieldName, this.prepend, this.append, this.order)
