package blockly2scafi.generators.categories.movement

import blockly2scafi.Orders
import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType

class Flocking extends ValueBlockType:

  override def name: String = "flocking"

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq()

  override def generator: Generator = (block) =>
    (
      """
      |FlockBehaviour(
      |    attractionForce = 0.001,
      |    alignmentForce = 0.1,
      |    repulsionForce = 0.5,
      |    separationDistance = 10.0,
      |).run()
      |""".stripMargin,
      this.order,
    )

  override def order: Order = Orders.ORDER_ATOMIC
end Flocking
