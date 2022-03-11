package main.scala.extractors.field
import main.scala.extractors.Extractable
import main.scala.extractors.Extractable.Order
import main.scala.extractors.AbstractExtractorBuilder

import scala.scalajs.js

class FieldExtractorBuilder extends AbstractExtractorBuilder {

  private var fieldName: String = "";

  def withFieldName(fieldName: String): FieldExtractorBuilder = {
    this.fieldName = fieldName;
    this
  }

  override def build: Extractable = new FieldExtractor(this.fieldName, this.prepend, this.append, this.order)

}
