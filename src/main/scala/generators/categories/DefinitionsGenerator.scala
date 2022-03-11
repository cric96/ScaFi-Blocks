package generators.categories

import blockly2scafi.{Block, Blockly, Orders}
import extractors.Extractable
import extractors.Extractable.Extractor
import extractors.field.FieldExtractor
import generators.Generator

object DefinitionsGenerator extends Generator {

  private def genericDefinitionGenerator = (name: String) => new Extractable {
    override def getExtractor: Extractor = (block: Block) => {
      val defName = block.getFieldValue("NAME")
      val input = Blockly.ScaFi.valueToCode(block, "VALUE", Orders.ORDER_NONE);
      (s"${name} ${defName} = ${input}", Orders.NONE)
    }
  }
  private val defineGenerator = genericDefinitionGenerator("def")
  private val valGenerator = genericDefinitionGenerator("val")
  private val varGenerator = genericDefinitionGenerator("var")

  private val getterExtractor = FieldExtractor.builder
    .withFieldName("NAME")
    .build

  override protected def directCodeGenerators: Map[String, Extractable] = Map(
    "define" -> defineGenerator,
    "val" -> valGenerator,
    "var" -> varGenerator
  )

  override protected def codeTupleGenerators: Map[String, Extractable] = Map(
    "getter" -> getterExtractor
  )
}
