package generators.categories

import blockly2scafi.Blockly
import generables.Generable
import generables.code.GenerableInput
import generators.CategoryBlocksGenerators

object Basic extends CategoryBlocksGenerators {

  private val output = GenerableInput.builder
    .withInputName("OUTPUT_VALUE")
    .build

  override protected def generableValueBlocks: Map[String, Generable] = Map()
  override protected def generableUnitBlocks: Map[String, Generable] = Map(
    "output" -> output
  )
}
