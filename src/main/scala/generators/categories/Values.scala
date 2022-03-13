package generators.categories

import blockly2scafi.Blockly
import generables.Generable
import generables.Generable.Generator
import generables.code.{GenerableInput, GenerableMultiInput}
import generables.field.GenerableField
import generators.CategoryBlocksGenerators

object Values extends CategoryBlocksGenerators {

  private val _generalValue = GenerableField.builder
    .withFieldName("VALUE")
    .build

  private val _doubleQuotedValue = GenerableField.builder
    .withFieldName("VALUE")
    .withPrepend("\"")
    .withAppend("\"")
    .build



  private val tupleValue = GenerableMultiInput.builder
    .withInputName("VALUE_1")
    .withInputName("VALUE_2")
    .withJoin(", ")
    .withPrepend("(")
    .withAppend(")")
    .build

  private val randomValue = GenerableInput.builder
    .withInputName("MAX")
    .withPrepend("Random.nextInt(")
    .withAppend(")")
    .build

  private val randomValueBetween = GenerableMultiInput.builder
    .withInputName("MIN")
    .withInputName("MAX")
    .withJoin(", ")
    .withPrepend("Random.between(")
    .withAppend(")")
    .build

  override protected def generableValueBlocks: Map[String, Generable] = Map[String, Generable](
    "integer_value" -> _generalValue,
    "double_value" -> _generalValue,
    "boolean_value" -> _generalValue,

    "string_value" -> _doubleQuotedValue,
    "color_value" -> _doubleQuotedValue,

    "tuple_value" -> tupleValue,

    "random_value" -> randomValue,
    "random_value_between" -> randomValueBetween
  )

  override protected def generableUnitBlocks: Map[String, Generable] = Map()
}
