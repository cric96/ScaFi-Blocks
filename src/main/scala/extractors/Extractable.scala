package main.scala.extractors
import scala.scalajs.js
import main.scala.blockly2scafi.Block;

trait Extractable {
    def extract: (Block) => (String, Extractable.Order)
}

object Extractable {
    type Order = Int
}
