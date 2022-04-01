package blockly2scafi

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
    "aggregate.RepBlockType",
    "aggregate.Lambda1ParamBlockType",
    "aggregate.Lambda2ParamBlockType",
    "fold.FoldHoodBlockType",
    "fold.FoldHoodPlusBlockType",
    "fold.MinHoodBlockType",
    "fold.MinHoodPlusBlockType",
    "utilities.MidBlockType",
    "utilities.DistanceToBlockType",
    "utilities.DistanceBetweenBlockType",
    "utilities.ChannelBlockType",
    "utilities.NbrRangeBlockType",
    "sensors.SenseBlockType",
    "actuators.LedAllToBlockType",
    "operations.EqualsBlockType",
    "operations.BooleanOperationBlockType",
    "operations.TernaryOperationBlockType",
    "operations.NumberCompareBlockType",
    "operations.NumberOperationBlockType",
    "values.StringValueBlockType",
    "values.IntegerValueBlockType",
    "values.DoubleValueBlockType",
    "values.BooleanValueBlockType",
    "values.ColorValueBlockType",
    "values.TupleValueBlockType",
    "values.Tuple3ValueBlockType",
    "values.RandomValueBlockType",
    "values.RandomValueBetweenBlockType",
    "types.IntegerClassBlockType",
    "types.DoubleClassBlockType",
    "types.BooleanClassBlockType",
    "types.StringClassBlockType",
    "types.OtherClassBlockType",
    "functions.ReturnBlockType",
    "functions.FunctionBlockType",
    "functions.Function1ParamBlockType",
    "functions.Function2ParamBlockType",
    "functions.Function3ParamBlockType",
    "functions.CallFunctionBlockType",
    "functions.CallFunction1ParamBlockType",
    "functions.CallFunction2ParamBlockType",
    "functions.CallFunction3ParamBlockType",
    "definitions.DefineBlockType",
    "definitions.ValBlockType",
    "definitions.VarBlockType",
    "definitions.GetterBlockType"

  )

}
