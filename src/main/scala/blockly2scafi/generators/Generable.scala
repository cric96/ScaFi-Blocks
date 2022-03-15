package blockly2scafi.generators

import blockly2scafi.Block
import blockly2scafi.Orders.Order
import blockly2scafi.generators.Generable.Generator

import scala.scalajs.js

/**
 * Interface that encapsulate the possibility of retrieving a Generator
 * from a class.
 */
trait Generable {

  /**
   * Get the generator of this class.
   *
   * @return the generator.
   */
  def generator: Generator

}

object Generable {
  /**
   * Code generation strategy based on the instance of the block.
   */
  type Generator = js.Function1[Block, js.Tuple2[String, Order]]
}
