package extractors.code

import blockly2scafi.Blockly
import extractors.Extractable
import extractors.Extractable.{Extractor, Order}

case class CodesExtractor(inputNames: Seq[String], join: String, prepend: String, append: String, internalOrder: Order, externalOrder: Order) extends Extractable {

  override def getExtractor: Extractor = (block) => {

    val code = this.inputNames.map(
      inputName => CodeExtractor.builder
        .withInputName(inputName)
        .withExternalOrder(this.internalOrder)
        .build
        .getExtractor(block)._1
    )

    (this.prepend + code.mkString(this.join) + this.append, this.externalOrder)

  }

}

object CodesExtractor {
  def builder: CodesExtractorBuilder = new CodesExtractorBuilder
}
