package blockly2scafi

import extractors.Extractable.{Extractor, Order}
import org.scalajs.dom.raw.Element

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
trait ScaFi extends js.Object {
  def addCodeTupleExtractor(blockName: String, codeTupleExtractor: Extractor): Unit = js.native
  def addDirectCodeExtractor(blockName: String, directCodeExtractor: Extractor): Unit = js.native;

  def workspaceToCode(workspace: Workspace): String = js.native
  def valueToCode(block: Block, inputName: String, internalOrder: Order): String = js.native
  def statementToCode(block: Block, blockName: String): String = js.native
  def blockToCode(block: Block, flag: Boolean): String = js.native
}

@js.native
trait Workspace extends js.Object {
  def getAllBlocks(): js.Array[Block] = js.native // parenthesis required for matching with js method!
  def addChangeListener(function: js.Function): Unit
}

@js.native
trait Block extends js.Object {
  def `type`: String = js.native // backtick required because type is a keyword in scala
  def workspace: Workspace = js.native
  def getFieldValue(fieldName: String): String = js.native
  def getInputTargetBlock(name: String): Block = js.native
}

object Orders {
  val NONE: Order = 99
  val ORDER_ATOMIC: Order = 0
  val ORDER_FUNCTION_CALL: Order = 2 //()
  val ORDER_MULTIPLICATION: Order = 5.1 // *
  val ORDER_DIVISION: Order = 5.2 // /
  val ORDER_MODULUS: Order = 5.3 // %
  val ORDER_SUBTRACTION: Order = 6.1 // -
  val ORDER_ADDITION: Order = 6.2 // +
  val ORDER_RELATIONAL: Order = 8 // < <= > >=
  val ORDER_EQUALITY: Order = 12 // == != === !==
  val ORDER_LOGICAL_AND: Order = 13 // &&
  val ORDER_LOGICAL_OR: Order = 14 // ||&&
  val ORDER_ASSIGNMENT: Order = 20 // =
  val ORDER_NONE: Order = 99
}

@js.native
@JSGlobal
object Blockly extends js.Object {
  def createBlockly2ScafiWorkspace(editor: Element): Workspace = js.native
  def ScaFi: ScaFi = js.native
}

