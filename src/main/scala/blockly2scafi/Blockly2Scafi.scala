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
}

@js.native
trait Workspace extends js.Object {
  def addChangeListener(function: js.Function): Unit
}

@js.native
trait Block extends js.Object {
  def getFieldValue(fieldName: String): String = js.native;
}

object Orders {
  val NONE: Order = 99
}

@js.native
@JSGlobal
object Blockly extends js.Object {
  def createBlockly2ScafiWorkspace(editor: Element): Workspace = js.native
  def ScaFi: ScaFi = js.native
}

