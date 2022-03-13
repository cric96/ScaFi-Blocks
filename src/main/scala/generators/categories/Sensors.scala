package generators.categories

import blockly2scafi.{Block, Blockly, Orders}
import generables.Generable
import generables.Generable.Generator
import generables.field.GenerableField
import generators.CategoryBlocksGenerators

object Sensors extends CategoryBlocksGenerators {

  private val sense = new Generable {
    override def generator: Generator = (block) => {
      val _type = Blockly.ScaFi.valueToCode(block, "TYPE", Orders.NONE)
      val name = Blockly.ScaFi.valueToCode(block, "SENSOR_NAME", Orders.NONE)
      val code = s"sense[${_type}](${name})"
      (code, Orders.NONE)
    }
  }

  override protected def generableValueBlocks: Map[String, Generable] = Map(
    "sense" -> sense
  )

  override protected def generableUnitBlocks: Map[String, Generable] = Map()
}
