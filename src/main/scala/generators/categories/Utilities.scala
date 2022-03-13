package generators.categories

import blockly2scafi.Orders
import generables.Generable
import generables.Generable.Generator
import generables.code.{GenerableInput, GenerableMultiInput}
import generators.CategoryBlocksGenerators

object Utilities extends CategoryBlocksGenerators{

  private val mid = new Generable {
    override def generator: Generator = (block) => ("mid", Orders.NONE)
  }

  private val distanceTo = GenerableInput.builder
    .withInputName("SRC")
    .withPrepend("distanceTo(")
    .withAppend(")")
    .build

  private val distanceBetween = GenerableMultiInput.builder
    .withInputName("SOURCE")
    .withInputName("TARGET")
    .withPrepend("distanceBetween(")
    .withAppend(")")
    .withJoin(", ")
    .build

  private val channel = GenerableMultiInput.builder
    .withInputName("SOURCE")
    .withInputName("TARGET")
    .withInputName("WIDTH")
    .withPrepend("channel(")
    .withAppend(")")
    .withJoin(", ")
    .build

  override protected def generableValueBlocks: Map[String, Generable] = Map(
    "mid" -> mid,
    "distance_to" -> distanceTo,
    "distance_between" -> distanceBetween,
    "channel" -> channel
  )
  override protected def generableUnitBlocks: Map[String, Generable] = Map()
}
