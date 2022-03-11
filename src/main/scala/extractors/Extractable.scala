package extractors

import blockly2scafi.Block
import extractors.Extractable.Extractor

import scala.scalajs.js

trait Extractable {
    def getExtractor: Extractor
}

object Extractable {
    type Order = Double
    type Extractor = js.Function1[Block, js.Tuple2[String, Order]]
}
