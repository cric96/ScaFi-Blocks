Blockly.ScaFi = new Blockly.Generator('ScaFi');

//scrub_ is the common tasks for generating code from blocks, called on every block.
Blockly.ScaFi.scrub_ = function(block, code, opt_thisOnly) {
    const nextBlock = block.nextConnection && block.nextConnection.targetBlock();
    let nextCode = '';
    if (nextBlock) {
        nextCode = opt_thisOnly ? '' : '\n' + Blockly.ScaFi.blockToCode(nextBlock);
    }
    return code + nextCode;
};

Blockly.ScaFi.addCodeTupleExtractor = function(blockName, extractor) {
    Blockly.ScaFi[blockName] = extractor;
}
Blockly.ScaFi.addDirectCodeExtractor = function(blockName, extractor) {
    Blockly.ScaFi[blockName] = (block) => extractor(block)[0];
}