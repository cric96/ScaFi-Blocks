package generators

import blockly2scafi.Blockly
import extractors.Extractable
import generators.categories._


trait Generator {

  def generators: Map[String, Extractable]

  def generate: Unit = {
    generators foreach { elem => Blockly.ScaFi.addGenerator(elem._1, elem._2.getExtractor) }
  }

}

object Generators {

  def generators: Seq[Generator] = Seq(
    ValuesGenerator,
    TypesGenerator
  )

  def generateAll: Unit = {
    generators foreach { _.generate }
  }
}
