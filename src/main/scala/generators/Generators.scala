package generators

import blockly2scafi.Blockly
import generables.Generable
import generators.categories._


trait CategoryBlocksGenerators {

  /**
   * Get the blocks that return somethings (blocks with a left connection).
   * @return the Map of blockName -> Extractable
   */
  protected def generableValueBlocks: Map[String, Generable]

  /**
   * Get the blocks that doesn't return anythings (blocks with an up/down connection).
   * @return the Map of blockName -> Extractable
   */
  protected def generableUnitBlocks: Map[String, Generable]

  /**
   * Add the code generation methods for the blocks of the category.
   */
  def addGeneratorsToBlockly(): Unit = {
    generableValueBlocks foreach { elem => Blockly.ScaFi.addValueBlockGenerator(elem._1, elem._2.generator) }
    generableUnitBlocks foreach { elem => Blockly.ScaFi.addUnitBlockGenerator(elem._1, elem._2.generator) }
  }
}

object Generators {

  private def generators: Seq[CategoryBlocksGenerators] = Seq(
    Basic,
    Aggregate,
    Utilities,
    Sensors,
    Actuators,
    Operators,
    Values,
    Types,
    Functions,
    Definitions
  )

  def addAllGeneratorsToBlockly(): Unit = generators foreach {_.addGeneratorsToBlockly()}
}
