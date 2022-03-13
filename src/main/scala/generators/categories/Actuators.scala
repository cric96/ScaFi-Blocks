package generators.categories

import generables.Generable
import generables.code.GenerableInput
import generators.CategoryBlocksGenerators

object Actuators extends CategoryBlocksGenerators {

  private val ledAllTo = GenerableInput.builder
    .withInputName("COLOR")
    .withPrepend("ledAll to ")
    .build

  override protected def generableValueBlocks: Map[String, Generable] = Map(
    "led_all_to" -> ledAllTo
  )

  override protected def generableUnitBlocks: Map[String, Generable] = Map()
}
