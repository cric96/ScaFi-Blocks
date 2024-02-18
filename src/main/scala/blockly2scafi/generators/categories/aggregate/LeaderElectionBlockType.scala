package blockly2scafi.generators.categories.aggregate

import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{Blockly, Orders}

class LeaderElectionBlockType extends ValueBlockType:
  override def name: String = "leaderElection"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq()

  override def generator: Generator = (block) =>
    val code = s"S(200, nbrRange)"
    (code, Orders.NONE)
