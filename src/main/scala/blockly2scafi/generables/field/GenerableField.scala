package blockly2scafi.generables.field

import blockly2scafi.Block
import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable
import blockly2scafi.generators.Generable.Generator

case class GenerableField(fieldName: String, prepend: String = "", append: String = "", order: Order) extends Generable:

  override def generator: Generator = (block: Block) =>
    (
      prepend + block.getFieldValue {
        fieldName
      } + append,
      order,
    )

object GenerableField:
  def builder: GenerableFieldBuilder = GenerableFieldBuilder()
