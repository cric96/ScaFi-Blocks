package generators.categories

import blockly2scafi.Blockly
import extractors.Extractable
import extractors.Extractable.Extractor
import extractors.code.{CodeExtractor, CodesExtractor}
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

  private val tupleValueExtractor = CodesExtractor.builder
    .withInputName("VALUE_1")
    .withInputName("VALUE_2")
    .withJoin(", ")
    .withPrepend("(")
    .withAppend(")")
    .build

  private val randomValueExtractor = CodeExtractor.builder
    .withInputName("MAX")
    .withPrepend("Random.nextInt(")
    .withAppend(")")
    .build

  private val randomValueBetweenExtractor = CodesExtractor.builder
    .withInputName("MIN")
    .withInputName("MAX")
    .withJoin(", ")
    .withPrepend("Random.between(")
    .withAppend(")")
    .build

  val generators = Map[String, Extractable](
    "integer_value" -> basicValueExtractor,
    "double_value" -> basicValueExtractor,
    "boolean_value" -> basicValueExtractor,

    "string_value" -> stringValueExtractor,
    "color_value" -> stringValueExtractor,

    "tuple_value" -> tupleValueExtractor,

    "random_value" -> randomValueExtractor,
    "random_value_between" -> randomValueBetweenExtractor
  )

}
