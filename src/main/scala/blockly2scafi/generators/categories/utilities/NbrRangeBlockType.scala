package blockly2scafi.generators.categories.utilities

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class NbrRangeBlockType extends ValueBlockType {

  override def name: String = "nbrrange"

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq()

  override def generator: Generator = (block) => ("nbrRange", this.order)

  override def order: Order = Orders.ORDER_ATOMIC

}
