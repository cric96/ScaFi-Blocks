const scafiGenerator = new Blockly.Generator('ScaFi');

//Operator precedence constants. See https://developers.google.com/blockly/guides/create-custom-blocks/operator-precedence
scafiGenerator.ORDER_ATOMIC = 0;
scafiGenerator.ORDER_FUNCTION_CALL = 2; //()
scafiGenerator.ORDER_MULTIPLICATION = 5.1; // *
scafiGenerator.ORDER_DIVISION = 5.2; // /
scafiGenerator.ORDER_MODULUS = 5.3; // %
scafiGenerator.ORDER_SUBTRACTION = 6.1; // -
scafiGenerator.ORDER_ADDITION = 6.2; // +
scafiGenerator.ORDER_RELATIONAL = 8; // < <= > >=
scafiGenerator.ORDER_EQUALITY = 12; // == != === !==
scafiGenerator.ORDER_LOGICAL_AND = 13; // &&
scafiGenerator.ORDER_LOGICAL_OR = 14; // ||&&
scafiGenerator.ORDER_ASSIGNMENT = 20; // =
scafiGenerator.ORDER_NONE = 99;

function extractValue(fieldName, prepend = "", append = "", order = scafiGenerator.ORDER_ATOMIC) {
    return (block) => [prepend + block.getFieldValue(fieldName) + append, order];
};

function extractValues(fieldNames, join, prepend = "", append = "", order = scafiGenerator.ORDER_ATOMIC) {
    return function(block) {
        let code = fieldNames.map(fieldName => extractValue(fieldName)(block)[0]);
        return [prepend + code.join(join) + append, order];
    }
}

function extractCode(fieldName, prepend = "", append = "", internalOrder = scafiGenerator.ORDER_NONE, externalOrder = scafiGenerator.ORDER_FUNCTION_CAL) {
    return (block) => [prepend + Blockly.ScaFi.valueToCode(block, fieldName, internalOrder) + append, externalOrder];
}

function extractCodes(fieldNames, join, prepend = "", append = "", internalOrder = scafiGenerator.ORDER_NONE, externalOrder = scafiGenerator.ORDER_FUNCTION_CALL) {
    return function(block) {
        let code = fieldNames.map(fieldName => extractCode(fieldName)(block)[0]);
        return [prepend + code.join(join) + append, externalOrder];
    }
}

/* AGGREGATE PROGRAM BLOCK */
scafiGenerator['aggregate_program'] = function(block) {

    const standardImportMap = {
        "random": ["java.util.Random"]
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
        if (block.type in standardImportArray) {
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

    let standardImportCode = standardImportArray.map(module => "import " + module + ";").join("\n");
    let scafiImportCode = "";

    if (scafiImportArray.length > 0) {
        scafiImportCode = "//using " + scafiImportArray.join(", ") + "\n";
    }

    const otherCode = Blockly.ScaFi.blockToCode(block.getInputTargetBlock("AGGREGATE_PROGRAM_MAIN")); //Not using statementToCode to avoid first INDENT

    return standardImportCode + scafiImportCode + otherCode;
}

scafiGenerator['output'] = (block) => extractCode('OUTPUT_VALUE')(block)[0];


// Types
scafiGenerator['class_integer'] = extractValue('NAME')
scafiGenerator['class_double'] = extractValue('NAME')
scafiGenerator['class_boolean'] = extractValue('NAME')
scafiGenerator['class_string'] = extractValue('NAME')
scafiGenerator['class_other'] = extractValue('NAME')

// Values
scafiGenerator['integer_value'] = extractValue('VALUE')
scafiGenerator['double_value'] = extractValue('VALUE')
scafiGenerator['boolean_value'] = extractValue('VALUE')
scafiGenerator['string_value'] = extractValue('VALUE', '"', '"')
scafiGenerator['color_value'] = extractValue('VALUE', '"', '"')
scafiGenerator['tuple_value'] = extractCodes(["VALUE_1", "VALUE_2"], ", ", "(", ")");






scafiGenerator['sense'] = (block) => {
    const type = Blockly.ScaFi.valueToCode(block, "TYPE", scafiGenerator.ORDER_NONE);
    const name = Blockly.ScaFi.valueToCode(block, "SENSOR_NAME", scafiGenerator.ORDER_NONE);
    const code = `sense[${type}](${name})`;
    return [code, scafiGenerator.ORDER_FUNCTION_CALL];
}


// Types

/* FUNCTIONS */
scafiGenerator['getter'] = extractValue('NAME')
scafiGenerator['mid'] = (block) => ["mid", scafiGenerator.ORDER_FUNCTION_CALL];

scafiGenerator['led_all_to'] = extractValue('COLOR_VALUE', 'ledAll to ', '', scafiGenerator.ORDER_FUNCTION_CALL)
scafiGenerator['distance_to'] = extractCode("SRC", "distanceTo(", ")", scafiGenerator.ORDER_ATOMIC)
scafiGenerator['distance_between'] = extractCodes(["SOURCE", "TARGET", ", ", "distanceBetween(", ")", scafiGenerator.ORDER_ATOMIC])
scafiGenerator['channel'] = extractCodes(["SOURCE", "TARGET", "WIDTH"], ", ", "channel(", ")", scafiGenerator.ORDER_ATOMIC)
scafiGenerator['mux'] = (block) => [
    extractCode("CONDITION", "mux(", ")")(block)[0] +
    extractCodes(["FIRST_BRANCH", "SECOND_BRANCH"], "\n}{\n\t", " {\n\t", "\n}")(block)[0],
    scafiGenerator.ORDER_FUNCTION_CALL
]

scafiGenerator['boolean_operation'] = function(block) {
    const operation = block.getFieldValue("OPERATION");

    let order;
    let operator;
    if (operation === 'and') {
        operator = "&&";
        order = scafiGenerator.ORDER_LOGICAL_AND;
    } else { //or
        operator = "||";
        order = scafiGenerator.ORDER_LOGICAL_OR;
    }

    const first = Blockly.ScaFi.valueToCode(block, 'FIRST', order);
    const second = Blockly.ScaFi.valueToCode(block, 'SECOND', order);
    const code = first + ' ' + operator + ' ' + second;

    return [code, order];
}
scafiGenerator['number_compare'] = function(block) {
    const operation = block.getFieldValue("OPERATOR");

    let operator;
    let order;
    if (operation === 'GREATER') {
        operator = ' > ';
        order = scafiGenerator.ORDER_RELATIONAL;
    } else if (operation === 'GREATER_OR_EQUAL') {
        operator = ' >= ';
        order = scafiGenerator.ORDER_RELATIONAL;
    } else if (operation === 'EQUAL') {
        operator = ' == ';
        order = scafiGenerator.ORDER_EQUALITY;
    } else if (operation === 'NOT_EQUAL') {
        operator = ' != ';
        order = scafiGenerator.ORDER_EQUALITY;
    } else if (operation === 'LESS_OR_EQUAL') {
        operator = ' <= ';
        order = scafiGenerator.ORDER_RELATIONAL;
    } else { //LESS
        operator = ' < ';
        order = scafiGenerator.ORDER_RELATIONAL;
    }
    const first = Blockly.ScaFi.valueToCode(block, 'FIRST', order);
    const second = Blockly.ScaFi.valueToCode(block, 'SECOND', order);
    const code = first + operator + second;

    return [code, order];
}
scafiGenerator['number_operation'] = function(block) {
    const operation = block.getFieldValue("OPERATOR");

    let order;
    let operator;
    if (operation === 'ADDITION') {
        operator = ' + ';
        order = scafiGenerator.ORDER_ADDITION;
    } else if (operation === 'SUBTRACTION') {
        operator = ' - ';
        order = scafiGenerator.ORDER_SUBTRACTION;
    } else if (operation === 'MULTIPLICATION') {
        operator = ' * ';
        order = scafiGenerator.ORDER_MULTIPLICATION;
    } else if (operation === 'DIVISION') {
        operator = ' / ';
        order = scafiGenerator.ORDER_DIVISION;
    } else { //MODULUS
        operator = ' % ';
        order = scafiGenerator.ORDER_MODULUS;
    }
    const first = Blockly.ScaFi.valueToCode(block, 'FIRST', order);
    const second = Blockly.ScaFi.valueToCode(block, 'SECOND', order);
    const code = first + operator + second;
    return [code, order];
}

scafiGenerator['define'] = function(block) {
    const defName = block.getFieldValue('NAME');
    const input = block.getInput('VALUE');
    const connection = input.connection;
    const targetBlock = connection.targetBlock();
    let type = null;
    if (targetBlock) {
        type = targetBlock.outputConnection.getCheck();
    }

    let code = "def " + defName;
    if (type && type.length) {
        code += " : " + type[0]
    }
    code += " = " + Blockly.ScaFi.valueToCode(block, "VALUE", scafiGenerator.ORDER_ASSIGNMENT);
    return code;
}
scafiGenerator['val'] = function(block) {
    const defName = block.getFieldValue('NAME');
    const input = block.getInput('VALUE');
    const connection = input.connection;
    const targetBlock = connection.targetBlock();
    let type = null;
    if (targetBlock) {
        type = targetBlock.outputConnection.getCheck();
    }

    let code = "val " + defName;
    if (type && type.length) {
        code += " : " + type[0]
    }
    code += " = " + Blockly.ScaFi.valueToCode(block, "VALUE", scafiGenerator.ORDER_ASSIGNMENT);
    return code;
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


Blockly.ScaFi = scafiGenerator;