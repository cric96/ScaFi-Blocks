package generators.categories

import extractors.Extractable
import extractors.field.FieldExtractor
import generators.Generator

object TypesGenerator extends Generator {

  private val classExtractor = FieldExtractor.builder
    .withFieldName("NAME")
    .build

  override def generators: Map[String, Extractable] = Map(
    "class_integer" -> classExtractor,
    "class_double" -> classExtractor,
    "class_boolean" -> classExtractor,
    "class_string" -> classExtractor,
    "class_other" -> classExtractor
  )

}
