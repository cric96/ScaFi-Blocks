package generators.categories

import blockly2scafi.{Block, Blockly, Orders}
import extractors.Extractable
import extractors.Extractable.Extractor
import extractors.field.FieldExtractor
import generators.Generator

object SensorsGenerator extends Generator {

  private val sensorExtractor = new Extractable {
    override def getExtractor: Extractor = (block) => {
      val _type = Blockly.ScaFi.valueToCode(block, "TYPE", Orders.NONE)
      val name = Blockly.ScaFi.valueToCode(block, "SENSOR_NAME", Orders.NONE)
      val code = s"sense[${_type}](${name})"
      (code, Orders.NONE)
    }
  }

  override protected def codeTupleGenerators: Map[String, Extractable] = Map(
    "sense" -> sensorExtractor
  )

  override protected def directCodeGenerators: Map[String, Extractable] = Map()
}
