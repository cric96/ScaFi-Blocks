package extractors.field
import extractors.Extractable
import extractors.Extractable.Order
import extractors.AbstractExtractorBuilder

import scala.scalajs.js

class FieldExtractorBuilder extends AbstractExtractorBuilder {

  private var fieldName: String = "";

  def withFieldName(fieldName: String): FieldExtractorBuilder = {
    this.fieldName = fieldName;
    this
  }

  override def build: Extractable = new FieldExtractor(this.fieldName, this.prepend, this.append, this.order)

}
