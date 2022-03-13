package generators.categories

import generables.Generable
import generables.field.GenerableField
import generators.CategoryBlocksGenerators

object Types extends CategoryBlocksGenerators {

  private val `class` = GenerableField.builder
    .withFieldName("NAME")
    .build

  override protected def generableValueBlocks: Map[String, Generable] = Map(
    "class_integer" -> `class`,
    "class_double" -> `class`,
    "class_boolean" -> `class`,
    "class_string" -> `class`,
    "class_other" -> `class`
  )

  override protected def generableUnitBlocks: Map[String, Generable] = Map()
}
