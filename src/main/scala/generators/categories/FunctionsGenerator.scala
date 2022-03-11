package generators.categories

import blockly2scafi.{Block, Blockly, Orders}
import extractors.Extractable
import extractors.Extractable.Extractor
import extractors.code.CodeExtractor
import extractors.field.FieldExtractor
import generators.Generator

object FunctionsGenerator extends Generator {

  private val returnExtractor = CodeExtractor.builder
    .withInputName("RETURN")
    .build

  private val functionExtractor = new Extractable {
    override def getExtractor: Extractor = (block: Block) => {
      val definition = block.getFieldValue("FUNCTION_NAME");
      val code = Blockly.ScaFi.statementToCode(block, "BODY");
      (s"def ${definition}:\n${code}\n", Orders.NONE)
    }
  }

  private val function1ParamExtractor = new Extractable {
    override def getExtractor: Extractor = (block: Block) => {
      val definition = block.getFieldValue("FUNCTION_NAME")
      val param = block.getFieldValue("PARAM_NAME")
      val _type = Blockly.ScaFi.valueToCode(block, "PARAM_TYPE", Orders.ORDER_NONE)
      val code = Blockly.ScaFi.statementToCode(block, "BODY")
      (s"def ${definition}(${param}: ${_type}):\n${code}\n", Orders.NONE)
    }
  }

  private val function2ParamExtractor = new Extractable {
    override def getExtractor: Extractor = ???
  }
  private val function3ParamExtractor = new Extractable {
    override def getExtractor: Extractor = ???
  }

  private val callFunctionExtractor = FieldExtractor.builder
    .withFieldName("NAME")
    .build

  private val callFunction1ParamExtractor = new Extractable {
    override def getExtractor: Extractor = (block: Block) => {
      val name = block.getFieldValue("NAME");
      val param = Blockly.ScaFi.valueToCode(block, "PARAM", Orders.ORDER_NONE);
      (s"${name}(${param})", Orders.ORDER_FUNCTION_CALL);
    }
  }

  private val callFunction2ParamExtractor = new Extractable {
    override def getExtractor: Extractor = (block: Block) => {
      val name = block.getFieldValue("NAME");
      val param = Blockly.ScaFi.valueToCode(block, "PARAM_1", Orders.ORDER_NONE);
      val param2 = Blockly.ScaFi.valueToCode(block, "PARAM_2", Orders.ORDER_NONE);
      (s"${name}(${param}, ${param2})", Orders.ORDER_FUNCTION_CALL);
    }
  }

  private val callFunction3ParamExtractor = new Extractable {
    override def getExtractor: Extractor = (block: Block) => {
      val name = block.getFieldValue("NAME");
      val param = Blockly.ScaFi.valueToCode(block, "PARAM_1", Orders.ORDER_NONE);
      val param2 = Blockly.ScaFi.valueToCode(block, "PARAM_2", Orders.ORDER_NONE);
      val param3 = Blockly.ScaFi.valueToCode(block, "PARAM_3", Orders.ORDER_NONE);
      (s"${name}(${param}, ${param2}, ${param3})", Orders.ORDER_FUNCTION_CALL);
    }
  }

  override protected def codeTupleGenerators: Map[String, Extractable] = Map(
    "call_function" -> callFunctionExtractor,
    "call_function_1_param" -> callFunction1ParamExtractor,
    "call_function_2_param" -> callFunction2ParamExtractor,
    "call_function_3_param" -> callFunction3ParamExtractor
  )

  override protected def directCodeGenerators: Map[String, Extractable] = Map(
    "return" -> returnExtractor,
    "function" -> functionExtractor,
    "function_1_param" -> function1ParamExtractor,
  )
}
