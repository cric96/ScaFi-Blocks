package generators.categories

import blockly2scafi.Blockly
import extractors.Extractable
import extractors.code.CodeExtractor
import generators.Generator

object BasicGenerator extends Generator{

  private val outputGenerator = CodeExtractor.builder
    .withInputName("OUTPUT_VALUE")
    .build

  override protected def codeTupleGenerators: Map[String, Extractable] = Map()
  override protected def directCodeGenerators: Map[String, Extractable] = Map(
    "output" -> outputGenerator
  )
}
