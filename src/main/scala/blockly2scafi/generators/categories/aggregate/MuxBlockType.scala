package blockly2scafi.generators.categories.aggregate

import blockly2scafi.Orders.Order
import blockly2scafi.generables.code.{ GenerableInput, GenerableMultiInput }
import blockly2scafi.generators.ValueBlockType
import blockly2scafi.{ Block, Orders }

class MuxBlockType extends ValueBlockType:

  private val _generableCondition = GenerableInput.builder
    .withInputName("CONDITION")
    .withPrepend(s"${name}(")
    .withAppend(") {\n  ")
    .build

  private val _generableBranches = GenerableMultiInput.builder
    .withInputName("FIRST_BRANCH")
    .withInputName("SECOND_BRANCH")
    .withJoin("\n}{\n  ")
    .withAppend("\n}")
    .build

  override def name: String = "mux"

  override def order: Order = Orders.ORDER_ATOMIC

  override def fieldNames: Seq[String] = Seq()

  override def inputNames: Seq[String] = Seq("CONDITION, FIRST_BRANCH, SECOND_BRANCH")

  override def generator = (block: Block) =>
    val condition = _generableCondition.generator.apply(block)
    val branches = _generableBranches.generator.apply(block)
    (condition._1 + branches._1, Orders.NONE)
end MuxBlockType
