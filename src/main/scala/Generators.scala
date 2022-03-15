import blockly2scafi.Blockly
import blockly2scafi.generators.{UnitBlockType, ValueBlockType}

import scala.scalajs.reflect.Reflect

object Generators {

  def addAllGeneratorsToBlockly(): Unit = {

    val classes = classNames
      .map {
        basePath + "." + _
      }
      .map {
        Reflect.lookupInstantiatableClass
      }
      .filter {
        _.isDefined
      }
      .map {
        _.get
      }
      .map {
        _.getConstructor()
      }
      .filter {
        _.isDefined
      }
      .map {
        _.get
      }
      .map {
        _.newInstance()
      }


    val unitBlockTypes = classes
      .filter {
        _.isInstanceOf[UnitBlockType]
      }
      .map {
        _.asInstanceOf[UnitBlockType]
      }

    val valueBlockTypes = classes
      .filter {
        _.isInstanceOf[ValueBlockType]
      }
      .map {
        _.asInstanceOf[ValueBlockType]
      }

    unitBlockTypes.foreach { block => Blockly.ScaFi.addUnitBlockGenerator(block.name, block.generator) }
    valueBlockTypes.foreach { block => Blockly.ScaFi.addValueBlockGenerator(block.name, block.generator) }

  }

  private def basePath: String = "blockly2scafi.generators.categories"

  private def classNames: Seq[String] = Seq(
    "basic.OutputBlockType",
    "aggregate.AggregateProgramBlockType",
    "aggregate.NbrBlockType",
    "aggregate.MuxBlockType",
    "aggregate.BranchBlockType",
    "utilities.MidBlockType",
    "utilities.DistanceToBlockType",
    "utilities.DistanceBetweenBlockType",
    "utilities.ChannelBlockType"
  )

}
