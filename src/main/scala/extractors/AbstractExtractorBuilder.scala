package extractors

trait AbstractExtractorBuilder {

  protected var prepend = "";
  protected var append = "";

	def withPrepend(prepend: String): this.type = {
		this.prepend = prepend;
		this
	}

	def withAppend(append: String): this.type = {
		this.append = append;
		this
	}

	def build: Extractable;

}
