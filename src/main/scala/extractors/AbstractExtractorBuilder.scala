package main.scala.extractors

trait AbstractExtractorBuilder {

  protected var prepend = "";
  protected var append = "";
  protected var order = 0;

	def withPrepend(prepend: String): this.type = {
		this.prepend = prepend;
		this
	}

	def withAppend(append: String): this.type = {
		this.append = append;
		this
	}

	def withOrder(order: Int): this.type = {
		this.order = order;
		this
	}

	def build: Extractable;

}
