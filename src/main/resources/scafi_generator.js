const scafiGenerator = new Blockly.Generator('ScaFi');

/* AGGREGATE PROGRAM BLOCK */
scafiGenerator['aggregate_program'] = function(block) {

    const standardImportMap = {
        "random_value": ["scala.util.Random"],
        "random_value_between": ["scala.util.Random"]
    }

    const scafiImportMap = {
        "distance_to": ["StandardSensors", "BlockG"],
        "distance_between": ["StandardSensors", "BlockG"],
        "channel": ["StandardSensors", "BlockG"],
        "led_all_to": ["Actuation"],
    }

    let standardImportArray = [];
    let scafiImportArray = [];

    const workspace = block.workspace;
    const allBlocks = workspace.getAllBlocks();

    for (const block of allBlocks) {
        if (block.type in standardImportMap) {
            let modules = standardImportMap[block.type];
            for (const module of modules) {
                if (!standardImportArray.includes(module)) {
                    standardImportArray.push(module);
                }
            }
        }
        if (block.type in scafiImportMap) {
            let modules = scafiImportMap[block.type];
            for (const module of modules) {
                if (!scafiImportArray.includes(module)) {
                    scafiImportArray.push(module);
                }
            }
        }
    }

    let standardImportCode = standardImportArray.map(module => "import " + module + ";").join("\n") + "\n";
    let scafiImportCode = "";

    if (scafiImportArray.length > 0) {
        scafiImportCode = "//using " + scafiImportArray.join(", ") + "\n";
    }

    const otherCode = Blockly.ScaFi.blockToCode(block.getInputTargetBlock('AGGREGATE_PROGRAM_MAIN'), false)
        //Not using statementToCode to avoid first INDENT

    return standardImportCode + scafiImportCode + otherCode;
}


//scrub_ is the common tasks for generating code from blocks, called on every block.
scafiGenerator.scrub_ = function(block, code, opt_thisOnly) {
    const nextBlock = block.nextConnection && block.nextConnection.targetBlock();
    let nextCode = '';
    if (nextBlock) {
        nextCode = opt_thisOnly ? '' : '\n' + scafiGenerator.blockToCode(nextBlock);
    }
    return code + nextCode;
};

scafiGenerator.addCodeTupleExtractor = function(blockName, extractor) {
    scafiGenerator[blockName] = extractor;
}
scafiGenerator.addDirectCodeExtractor = function(blockName, extractor) {
    scafiGenerator[blockName] = (block) => extractor(block)[0];
}

Blockly.ScaFi = scafiGenerator;