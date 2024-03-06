package blockly2scafi.generators.categories.movement

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class Brownian extends ValueBlockType:

  override def name: String = "random_movement"

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq()

  override def generator: Generator = (block) => ("maintainTrajectory(brownian(0.5))(20)", this.order)

  override def order: Order = Orders.ORDER_ATOMIC
