package blockly2scafi.generators.categories.aggregate

import blockly2scafi.Orders.Order
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{ Blockly, Orders }

class RepBlockType extends ValueBlockType:

  override def name: String = "rep"

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("START", "EVOLUTION")

  override def generator = (block) =>
    val start = Blockly.ScaFi.valueToCode(block, "START", this.order)
    val evolution = Blockly.ScaFi.valueToCode(block, "EVOLUTION", this.order)

    (s"rep(${start}) { ${evolution} }", this.order)

  override def order: Order = Orders.NONE
