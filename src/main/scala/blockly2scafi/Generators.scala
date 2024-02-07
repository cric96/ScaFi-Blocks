package blockly2scafi

import scala.scalajs.reflect.Reflect
import blockly2scafi.generators.{UnitBlockType, ValueBlockType}
import blockly2scafi.macros.GeneratorsList

object Generators:

  def addAllGeneratorsToBlockly(): Unit =
    val unitBlockTypes: List[UnitBlockType] = GeneratorsList.searchByType[UnitBlockType](basePath)
    val valueBlockTypes: List[ValueBlockType] =  GeneratorsList.searchByType[ValueBlockType](basePath)

    unitBlockTypes.foreach { block => Blockly.ScaFi.addUnitBlockGenerator(block.name, block.generator) }
    valueBlockTypes.foreach { block => Blockly.ScaFi.addValueBlockGenerator(block.name, block.generator) }
  end addAllGeneratorsToBlockly

  inline val basePath: "blockly2scafi.generators.categories" = "blockly2scafi.generators.categories"
end Generators
