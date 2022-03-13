package generators.categories

import blockly2scafi.{Block, Blockly, Orders}
import generables.Generable
import generables.Generable.Generator
import generables.code.GenerableInput
import generables.field.GenerableField
import generators.CategoryBlocksGenerators

object Functions extends CategoryBlocksGenerators {

  private val `return` = GenerableInput.builder
    .withInputName("RETURN")
    .build

  private val function = new Generable {
    override def generator: Generator = (block: Block) => {
      val definition = block.getFieldValue("FUNCTION_NAME");
      val code = Blockly.ScaFi.statementToCode(block, "BODY");
      (s"def ${definition}:\n${code}\n", Orders.NONE)
    }
  }

  private val function1Param = new Generable {
    override def generator: Generator = (block: Block) => {
      val definition = block.getFieldValue("FUNCTION_NAME")
      val param = block.getFieldValue("PARAM_NAME")
      val _type = Blockly.ScaFi.valueToCode(block, "PARAM_TYPE", Orders.ORDER_NONE)
      val code = Blockly.ScaFi.statementToCode(block, "BODY")
      (s"def ${definition}(${param}: ${_type}):\n${code}\n", Orders.NONE)
    }
  }

  private val function2Param = new Generable {
    override def generator: Generator = (block: Block) => {
      val definition = block.getFieldValue("FUNCTION_NAME")
      val param1 = block.getFieldValue("PARAM_1_NAME")
      val param1type = Blockly.ScaFi.valueToCode(block, "PARAM_1_TYPE", Orders.ORDER_NONE)
      val param2 = block.getFieldValue("PARAM_2_NAME")
      val param2type = Blockly.ScaFi.valueToCode(block, "PARAM_2_TYPE", Orders.ORDER_NONE)
      val code = Blockly.ScaFi.statementToCode(block, "BODY")
      (s"def ${definition}(${param1}: ${param1type}, ${param2}: ${param2type}):\n${code}\n", Orders.NONE)
    }
  }
  private val function3Param = new Generable {
    override def generator: Generator = (block: Block) => {
      val definition = block.getFieldValue("FUNCTION_NAME")
      val param1 = block.getFieldValue("PARAM_1_NAME")
      val param1type = Blockly.ScaFi.valueToCode(block, "PARAM_1_TYPE", Orders.ORDER_NONE)
      val param2 = block.getFieldValue("PARAM_2_NAME")
      val param2type = Blockly.ScaFi.valueToCode(block, "PARAM_2_TYPE", Orders.ORDER_NONE)
      val param3 = block.getFieldValue("PARAM_3_NAME")
      val param3type = Blockly.ScaFi.valueToCode(block, "PARAM_3_TYPE", Orders.ORDER_NONE)
      val code = Blockly.ScaFi.statementToCode(block, "BODY")
      (s"def ${definition}(${param1}: ${param1type}, ${param2}: ${param2type}, ${param3}: ${param3type}):\n${code}\n", Orders.NONE)
    }
  }

  private val callFunction = GenerableField.builder
    .withFieldName("NAME")
    .build

  private val callFunction1Param = new Generable {
    override def generator: Generator = (block: Block) => {
      val name = block.getFieldValue("NAME");
      val param = Blockly.ScaFi.valueToCode(block, "PARAM", Orders.ORDER_NONE);
      (s"${name}(${param})", Orders.ORDER_FUNCTION_CALL);
    }
  }

  private val callFunction2Param = new Generable {
    override def generator: Generator = (block: Block) => {
      val name = block.getFieldValue("NAME");
      val param = Blockly.ScaFi.valueToCode(block, "PARAM_1", Orders.ORDER_NONE);
      val param2 = Blockly.ScaFi.valueToCode(block, "PARAM_2", Orders.ORDER_NONE);
      (s"${name}(${param}, ${param2})", Orders.ORDER_FUNCTION_CALL);
    }
  }

  private val callFunction3Param = new Generable {
    override def generator: Generator = (block: Block) => {
      val name = block.getFieldValue("NAME");
      val param = Blockly.ScaFi.valueToCode(block, "PARAM_1", Orders.ORDER_NONE);
      val param2 = Blockly.ScaFi.valueToCode(block, "PARAM_2", Orders.ORDER_NONE);
      val param3 = Blockly.ScaFi.valueToCode(block, "PARAM_3", Orders.ORDER_NONE);
      (s"${name}(${param}, ${param2}, ${param3})", Orders.ORDER_FUNCTION_CALL);
    }
  }

  override protected def generableValueBlocks: Map[String, Generable] = Map(
    "call_function" -> callFunction,
    "call_function_1_param" -> callFunction1Param,
    "call_function_2_param" -> callFunction2Param,
    "call_function_3_param" -> callFunction3Param
  )

  override protected def generableUnitBlocks: Map[String, Generable] = Map(
    "return" -> `return`,
    "function" -> function,
    "function_1_param" -> function1Param,
    "function_2_param" -> function2Param,
    "function_3_param" -> function3Param
  )
}
