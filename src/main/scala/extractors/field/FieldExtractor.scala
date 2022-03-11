package extractors.field
import extractors.Extractable
import extractors.Extractable._
import blockly2scafi.Block

case class FieldExtractor(fieldName: String, prepend: String = "", append: String = "", order: Order) extends Extractable {
  override def getExtractor: Extractor = (block: Block) => (prepend + block.getFieldValue{fieldName} + append, order)
}

object FieldExtractor {
    def builder = new FieldExtractorBuilder()
}
