package main.scala

import org.scalajs.dom.document

import scala.scalajs.js
import main.scala.blockly2scafi.Blockly

object Configuration {
  val blocklyEditorId = "blockly-editor"
  val generatedCodeId = "generated-code"
}

object App {

  def setupWorkspace: Unit = {
    val toolboxElement = document.getElementById("toolbox")
    val blocklyEditorElement = document.getElementById(Configuration.blocklyEditorId)
    val generatedCodeElement = document.getElementById(Configuration.generatedCodeId)
    val workspace = Blockly.createBlockly2ScafiWorkspace(blocklyEditorElement)

    workspace.addChangeListener(() => {
      generatedCodeElement.textContent = Blockly.ScaFi.workspaceToCode(workspace)
    })
  }

  def main(args: Array[String]): Unit = {
    this.setupWorkspace
  }
}
