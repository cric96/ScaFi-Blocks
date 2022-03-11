package generators.categories

import blockly2scafi.Blockly
import extractors.Extractable
import extractors.Extractable.Extractor
import extractors.code.CodeExtractor
import generators.Generator

object ActuatorsGenerator extends Generator {

  private val ledAllToExtractor = CodeExtractor.builder
    .withInputName("COLOR")
    .withPrepend("ledAll to ")
    .build

  override protected def codeTupleGenerators: Map[String, Extractable] = Map(
    "led_all_to" -> ledAllToExtractor
  )

  override protected def directCodeGenerators: Map[String, Extractable] = Map()
}
