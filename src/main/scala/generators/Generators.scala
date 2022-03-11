package generators

import blockly2scafi.Blockly
import extractors.Extractable
import generators.categories._


trait Generator {
  protected def codeTupleGenerators: Map[String, Extractable]
  protected def directCodeGenerators: Map[String, Extractable]
  def generate: Unit = {
    codeTupleGenerators foreach { elem => Blockly.ScaFi.addCodeTupleExtractor(elem._1, elem._2.getExtractor) }
    directCodeGenerators foreach { elem => Blockly.ScaFi.addDirectCodeExtractor(elem._1, elem._2.getExtractor) }
  }
}

object Generators {

  private def generators: Seq[Generator] = Seq(
    BasicGenerator,
    AggregateGenerator,
    UtilitiesGenerator,
    SensorsGenerator,
    ActuatorsGenerator,
    ValuesGenerator,
    TypesGenerator
  )

  def generateAll: Unit = generators foreach {_.generate}
}
