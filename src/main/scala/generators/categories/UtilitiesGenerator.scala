package generators.categories

import blockly2scafi.Orders
import extractors.Extractable
import extractors.Extractable.Extractor
import extractors.code.{CodeExtractor, CodesExtractor}
import generators.Generator

object UtilitiesGenerator extends Generator{

  private val midGenerator = new Extractable {
    override def getExtractor: Extractor = (block) => ("mid", Orders.NONE)
  }

  private val distanceToGenerator = CodeExtractor.builder
    .withInputName("SRC")
    .withPrepend("distanceTo(")
    .withAppend(")")
    .build

  private val distanceBetweenGenerator = CodesExtractor.builder
    .withInputName("SOURCE")
    .withInputName("TARGET")
    .withPrepend("distanceBetween(")
    .withAppend(")")
    .withJoin(", ")
    .build

  private val channelGenerator = CodesExtractor.builder
    .withInputName("SOURCE")
    .withInputName("TARGET")
    .withInputName("WIDTH")
    .withPrepend("channel(")
    .withAppend(")")
    .withJoin(", ")
    .build

  override protected def codeTupleGenerators: Map[String, Extractable] = Map(
    "mid" -> midGenerator,
    "distance_to" -> distanceToGenerator,
    "distance_between" -> distanceBetweenGenerator,
    "channel" -> channelGenerator
  )
  override protected def directCodeGenerators: Map[String, Extractable] = Map()
}
