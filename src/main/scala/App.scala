import blockly2scafi.{ Blockly, Generators }
import org.scalajs.dom.{ document, window }
import blockly2scafi.macros.GeneratorsList
import blockly2scafi.generators.{ UnitBlockType, ValueBlockType }

import scala.scalajs.js.annotation.JSExportTopLevel

/**
 * Configuration for the Blockly editor.
 */
object Configuration:
  val blocklyEditorId = "blockly-editor"
  val generatedCodeId = "generated-code"
  val toolboxCodeId = "toolbox"

/**
 * Entry point for the ScalaJS application.
 */
object App:

  val valueBlockTypes = GeneratorsList.searchByType[ValueBlockType]("blockly2scafi.generators.categories")
  val unitBlockType = GeneratorsList.searchByType[UnitBlockType]("blockly2scafi.generators.categories")

  def main(args: Array[String]): Unit =
    this.setupWorkspace()
    this.setupGenerators()

  /**
   * Setup the blockly editor and the generated code area.
   */
  def setupWorkspace(): Unit =
    scalajs.js.Dynamic.global.window.ScafiBlocks = ""
    // val toolboxElement = document.getElementById(Configuration.toolboxCodeId)
    val blocklyEditorElement = document.getElementById(Configuration.blocklyEditorId)
    val generatedCodeElement = document.getElementById(Configuration.generatedCodeId)
    val workspace = Blockly.createBlockly2ScafiWorkspace(blocklyEditorElement)
    workspace.addChangeListener(() => scalajs.js.Dynamic.global.ScafiBlocks = Blockly.ScaFi.workspaceToCode(workspace))
    if (generatedCodeElement != null)
      workspace.addChangeListener(() => generatedCodeElement.textContent = Blockly.ScaFi.workspaceToCode(workspace))
    // if (toolboxElement != null) }

  def setupGenerators(): Unit =
    Generators.addAllGeneratorsToBlockly()
end App
