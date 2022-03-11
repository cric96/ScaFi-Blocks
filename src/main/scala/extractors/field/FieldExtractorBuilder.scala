package extractors.field
import extractors.Extractable
import extractors.AbstractExtractorBuilder
import extractors.Extractable.Order


class FieldExtractorBuilder extends AbstractExtractorBuilder {

  private var fieldName: String = "";
  private var order: Order = 0;

  def withFieldName(fieldName: String): FieldExtractorBuilder = {
    this.fieldName = fieldName;
    this
  }

  def withOrder(order: Order) : FieldExtractorBuilder = {
    this.order = order;
    this
  }

  override def build: Extractable = new FieldExtractor(this.fieldName, this.prepend, this.append, this.order)
}
