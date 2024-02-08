import blockly2scafi.{ Blockly, Generators }
import org.scalajs.dom.document
import blockly2scafi.macros.GeneratorsList
import blockly2scafi.generators.{ValueBlockType, UnitBlockType}

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
    val toolboxElement = document.getElementById(Configuration.toolboxCodeId)
    val blocklyEditorElement = document.getElementById(Configuration.blocklyEditorId)
    val generatedCodeElement = document.getElementById(Configuration.generatedCodeId)
    val workspace = Blockly.createBlockly2ScafiWorkspace(blocklyEditorElement)

    workspace.addChangeListener(() => generatedCodeElement.textContent = Blockly.ScaFi.workspaceToCode(workspace))

  def setupGenerators(): Unit =
    Generators.addAllGeneratorsToBlockly()
end App
