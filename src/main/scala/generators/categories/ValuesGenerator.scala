package generators.categories

import blockly2scafi.Blockly
import extractors.Extractable
import extractors.Extractable.Extractor
import extractors.field.FieldExtractor
import generators.Generator

object ValuesGenerator extends Generator {

  private val basicValueExtractor = FieldExtractor.builder
    .withFieldName("VALUE")
    .build

  private val stringValueExtractor = FieldExtractor.builder
    .withFieldName("VALUE")
    .withPrepend("\"")
    .withAppend("\"")
    .build

  val generators = Map[String, Extractable](
    "integer_value" -> basicValueExtractor,
    "double_value" -> basicValueExtractor,
    "boolean_value" -> basicValueExtractor,

    "string_value" -> stringValueExtractor,
    "color_value" -> stringValueExtractor
  )

}
