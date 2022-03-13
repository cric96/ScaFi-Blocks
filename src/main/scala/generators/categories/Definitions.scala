package generators.categories

import blockly2scafi.{Block, Blockly, Orders}
import generables.Generable
import generables.Generable.Generator
import generables.field.GenerableField
import generators.CategoryBlocksGenerators

object Definitions extends CategoryBlocksGenerators {

  private def _generableDefinitionFromName(name: String): Generable = new Generable {
    override def generator: Generator = (block: Block) => {
      val defName = block.getFieldValue("NAME")
      val input = Blockly.ScaFi.valueToCode(block, "VALUE", Orders.ORDER_NONE);
      (s"${name} ${defName} = ${input}", Orders.NONE)
    }
  }
  private val define = _generableDefinitionFromName("def")
  private val `val` = _generableDefinitionFromName("val")
  private val `var` = _generableDefinitionFromName("var")

  private val getter = GenerableField.builder
    .withFieldName("NAME")
    .build

  override protected def generableUnitBlocks: Map[String, Generable] = Map(
    "define" -> define,
    "val" -> `val`,
    "var" -> `var`
  )

  override protected def generableValueBlocks: Map[String, Generable] = Map(
    "getter" -> getter
  )
}
