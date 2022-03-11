package extractors.field
import extractors.Extractable
import extractors.Extractable.Order

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import main.scala.blockly2scafi.Block

class FieldExtractor(fieldName: String, prepend: String = "", append: String = "", order: Order) extends Extractable {

  override def extract: (Block) => (String, Order) = (block: Block) => (prepend + block.getFieldValue{fieldName} + append, order)

}

object FieldExtractor {
    def builder() = new FieldExtractorBuilder()
}
