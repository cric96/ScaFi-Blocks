package blockly2scafi.generators

/**
 * Block's category.
 */
trait Category {

  /**
   * The name of this category.
   *
   * @return the name of this category
   */
  def name: String

  /**
   * The unit block's types of this category.
   *
   * @return the sequence of UnitBlockType.
   */
  def unitBlockTypes: Seq[UnitBlockType]

  /**
   * The value block's types of this category.
   *
   * @return the sequence of ValueBlockType.
   */
  def valueBlockTypes: Seq[ValueBlockType]
}

