package blockly2scafi.generators.categories.movement

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class StandStill extends ValueBlockType:

  override def name: String = "stand_still"

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq()

  override def generator: Generator = (block) => ("standStill", this.order)

  override def order: Order = Orders.ORDER_ATOMIC
