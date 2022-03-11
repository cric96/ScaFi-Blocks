package extractors.code
import extractors.Extractable

class CodesExtractorBuilder extends CodeExtractorBuilder {

  private var inputNames: Seq[String] = Seq[String]()
  private var join = "";

  override def withInputName(inputName: String): this.type = {
    this.inputNames :+= inputName
    this
  }

  def withJoin(join: String): this.type = {
    this.join = join;
    this
  }

  override def build: Extractable = new CodesExtractor(this.inputNames, this.join, this.prepend, this.append, this.internalOrder, this.externalOrder)

}
