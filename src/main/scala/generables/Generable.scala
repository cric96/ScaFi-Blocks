package generables

import blockly2scafi.Block
import blockly2scafi.Orders.Order
import generables.Generable.Generator

import scala.scalajs.js

trait Generable {
    def generator: Generator
}

object Generable {
    type Generator = js.Function1[Block, js.Tuple2[String, Order]]
}
