package blockly2scafi.generators

import blockly2scafi.Orders.Order

import scala.scalajs.reflect.annotation.EnableReflectiveInstantiation


/**
 * Definition of a type of block in the environment.
 * It implements the Generable interface because each
 * block type need a code generation strategy.
 *
 * Annotated with @EnableReflectiveInstantiation for enable
 * some basic reflecting operation at runtime on its derived classes.
 */
@EnableReflectiveInstantiation
trait BlockType extends Generable {

  /**
   * The name of this block type.
   *
   * @return the name.
   */
  def name: String

  /**
   * Get the order of this operation block.
   *
   * @return the Order.
   */
  def order: Order

  /**
   * The names of the fields of this block type.
   *
   * @return the sequence of field's names.
   */
  def fieldNames: Seq[String]

  /**
   * The names of the inputs of this block type.
   *
   * @return the sequence of input's names.
   */
  def inputNames: Seq[String]

}





