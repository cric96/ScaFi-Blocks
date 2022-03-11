package extractors.code

import blockly2scafi.Blockly
import extractors.Extractable
import extractors.Extractable.{Extractor, Order}

case class CodeExtractor(inputName: String, prepend: String, append: String, internalOrder: Order, externalOrder: Order) extends Extractable {

  override def getExtractor: Extractor = (block) =>
    (
      this.prepend
      + Blockly.ScaFi.valueToCode(block, this.inputName, this.internalOrder)
      + this.append,
      this.externalOrder
    )
}

object CodeExtractor {
  def builder: CodeExtractorBuilder = new CodeExtractorBuilder()
}