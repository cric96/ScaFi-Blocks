package extractors.code

import extractors.Extractable.Order
import extractors.{AbstractExtractorBuilder, Extractable}

class CodeExtractorBuilder extends AbstractExtractorBuilder {

  private var inputName: String = "";
  private var internalOrder: Order = 1;
  private var externalOrder: Order = 1;

  def withFieldName(inputName: String): this.type = {
    this.inputName = inputName;
    this
  }

  def withInternalOrder(internalOrder: Order): this.type = {
    this.internalOrder = internalOrder;
    this
  }

  def withExternalOrder(externalOrder: Order): this.type = {
    this.externalOrder = externalOrder;
    this
  }

  override def build: Extractable = new CodeExtractor(this.inputName, this.prepend, this.append, this.internalOrder, this.externalOrder)
}
