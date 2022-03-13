package generators.categories

import blockly2scafi.{Block, Blockly, Orders}
import generables.Generable
import generables.Generable.Generator
import generables.code.{GenerableInput, GenerableMultiInput}
import generators.CategoryBlocksGenerators

object Aggregate extends CategoryBlocksGenerators {

  private val aggregateProgram = new Generable {

    override def generator: Generator = (block: Block) => {
      val standardImportMap = Map(
        "random_value" -> Seq("scala.util.Random"),
        "random_value_between" -> Seq("scala.util.Random")
      )

      val scafiImportMap = Map(
        "distance_to" -> Seq("StandardSensors", "BlockG"),
        "distance_between" -> Seq("StandardSensors", "BlockG"),
        "channel" -> Seq("StandardSensors", "BlockG"),
        "distance_between" -> Seq("Actuation")
      )

      var standardImportArray = Seq[String]()
      var scafiImportArray = Seq[String]()

      val workspace = block.workspace;
      val allBlocks = workspace.getAllBlocks()

      allBlocks.foreach(_block => {
        if (standardImportMap.contains(_block.`type`)) {
          val modules = standardImportMap(_block.`type`)
          modules.foreach(module => {
            if (!standardImportArray.contains(module)) {
              standardImportArray = standardImportArray :+ module
            }
          })
        }

        if (scafiImportMap.contains(_block.`type`)){
          val modules = scafiImportMap(_block.`type`)
          modules.foreach(module => {
            if (!scafiImportArray.contains(module)){
              scafiImportArray = scafiImportArray :+ module
            }
          })
        }
      })

      val standardImportCode = standardImportArray.map(module => "import " + module + ";").mkString("\n") + "\n";
      var scafiImportCode = "";

      if (scafiImportArray.nonEmpty) {
        scafiImportCode = "//using " + scafiImportArray.mkString(", ") + "\n";
      }

      val otherCode = Blockly.ScaFi.blockToCode(block.getInputTargetBlock("AGGREGATE_PROGRAM_MAIN"), false)
      //Not using statementToCode to avoid first INDENT

      (standardImportCode + scafiImportCode + otherCode, Orders.NONE)
    }
  }

  private val nbr = GenerableInput.builder
    .withInputName("EXPRESSION")
    .withPrepend("nbr { ")
    .withAppend(" }")
    .build

  private val _generableCondition = (name: String) => GenerableInput.builder
    .withInputName("CONDITION")
    .withPrepend(s"${name}(")
    .withAppend(") {\n  ")
    .build
  private val _generableBranches = GenerableMultiInput.builder
    .withInputName("FIRST_BRANCH")
    .withInputName("SECOND_BRANCH")
    .withJoin("\n}{\n  ")
    .withAppend("\n}")
    .build
  private val _generableDecision = (name: String) => new Generable {
    override def generator: Generator = (block: Block) => {
      val condition = _generableCondition(name).generator.apply(block)
      val branches = _generableBranches.generator.apply(block)
      (condition._1 + branches._1, Orders.NONE)
    }
  }
  private val mux = _generableDecision("mux")
  private val branch = _generableDecision("branch")

  override protected def generableValueBlocks: Map[String, Generable] = Map(
    "nbr" -> nbr,
    "mux" -> mux,
    "branch" -> branch
  )
  override protected def generableUnitBlocks: Map[String, Generable] = Map(
    "aggregate_program" -> aggregateProgram
  )
}
