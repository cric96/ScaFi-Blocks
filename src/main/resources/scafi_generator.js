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


// #region Builders
class AbstractExtractorBuilder {

    constructor() {
        this.prepend = "";
        this.append = "";
        this.order = scafiGenerator.ORDER_NONE;
    }

    withPrepend(prepend) {
        this.prepend = prepend;
        return this;
    }

    withAppend(append) {
        this.append = append;
        return this;
    }

    withOrder(order) {
        this.order = order;
        return this;
    }
}

/*
class FieldExtractor {

    constructor(fieldName, prepend, append, order) {
        this.fieldName = fieldName;
        this.prepend = prepend;
        this.append = append;
        this.order = order;
    }

    getExtractor() {
        return (block) => [this.prepend + block.getFieldValue(this.fieldName) + this.append, this.order];
    }

    static builder() {
        return new FieldExtractorBuilder();
    }

}
class FieldExtractorBuilder extends AbstractExtractorBuilder {

    constructor() {
        super();
        this.fieldName = "";
    }

    withFieldName(fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    build() {
        return new FieldExtractor(this.fieldName, this.prepend, this.append, this.order)
            .getExtractor();
    }
}
class FieldsExtractor {
    constructor(fieldNames, join, prepend, append, order) {
        this.fieldNames = fieldNames;
        this.join = join;
        this.prepend = prepend;
        this.append = append;
        this.order = order;
    }

    getExtractor() {
        return function(block) {
            let code = this.fieldNames.map(fieldName => extractFieldValue(fieldName)(block)[0]);
            return [this.prepend + code.join(this.join) + this.append, this.order];
        }
    }

    static builder() {
        return new FieldsExtractorBuilder();
    }
}
class FieldsExtractorBuilder extends AbstractExtractorBuilder {

    constructor() {
        this.fieldNames = [];
        this.join = "";
    }

    withFieldName(fieldName) {
        this.fieldNames.push(fieldName);
        return this;
    }

    withJoin(join) {
        this.join = join;
        return this;
    }

    build() {
        return new FieldsExtractor(this.fieldNames, this.join, this.prepend, this.append, this.order)
            .getExtractor();
    }
}
*/
class CodeExtractor {

    constructor(inputName, prepend, append, internalOrder, externalOrder) {
        this.inputName = inputName;
        this.prepend = prepend;
        this.append = append;
        this.internalOrder = internalOrder;
        this.externalOrder = externalOrder;
    }

    getExtractor() {
        return (block) => [

            this.prepend +
            Blockly.ScaFi.valueToCode(block, this.inputName, this.internalOrder) +
            this.append,

            this.externalOrder
        ];
    }

    static builder() {
        return new CodeExtractorBuilder();
    }
}
class CodeExtractorBuilder {
    constructor() {
        this.inputName = "";
        this.prepend = "";
        this.append = "";
        this.internalOrder = scafiGenerator.ORDER_NONE;
        this.externalOrder = scafiGenerator.ORDER_NONE;
    }

    withInputName(inputName) {
        this.inputName = inputName;
        return this;
    }

    withPrepend(prepend) {
        this.prepend = prepend;
        return this;
    }

    withAppend(append) {
        this.append = append;
        return this;
    }

    withInternalOrder(internalOrder) {
        this.internalOrder = internalOrder;
        return this;
    }

    withExternalOrder(externalOrder) {
        this.externalOrder = externalOrder;
        return this;
    }

    build() {
        return new CodeExtractor(this.inputName, this.prepend, this.append, this.internalOrder, this.externalOrder)
            .getExtractor();
    }
}
class CodesExtractor {

    constructor(inputNames, join, prepend, append, internalOrder, externalOrder) {
        this.inputNames = inputNames;
        this.join = join;
        this.prepend = prepend;
        this.append = append;
        this.internalOrder = internalOrder;
        this.externalOrder = externalOrder;
    }

    getExtractor() {
        return (block) => {
            let code = this.inputNames
                .map(inputName => CodeExtractor.builder()
                    .withInputName(inputName)
                    .withExternalOrder(this.internalOrder)
                    .build()(block)[0]
                );

            return [this.prepend + code.join(this.join) + this.append, this.externalOrder];
        }
    }

    static builder() {
        return new CodesExtractorBuilder();
    }
}
class CodesExtractorBuilder extends CodeExtractorBuilder {

    constructor() {
        super();
        this.inputNames = [];
        this.join = "";
    }

    withJoin(join) {
        this.join = join;
        return this;
    }

    withInputName(inputName) {
        this.inputNames.push(inputName);
        return this;
    }

    build() {
        return new CodesExtractor(this.inputNames, this.join, this.prepend, this.append, this.internalOrder, this.externalOrder)
            .getExtractor();
    }
}

// #endregion


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


// #region Basic

let outputExtractor = CodeExtractor.builder()
    .withInputName("OUTPUT_VALUE")
    .build()
scafiGenerator['output'] = (block) => outputExtractor(block)[0];

// #endregion


// #region Aggregate

scafiGenerator['nbr'] = CodeExtractor.builder()
    .withInputName("EXPRESSION")
    .withPrepend("nbr { ")
    .withAppend(" }")
    .build()

scafiGenerator['branch'] = (block) => {

    let conditionExtractor = CodeExtractor.builder()
        .withInputName("CONDITION")
        .withPrepend("branch(")
        .withAppend(") {\n  ")
        .build();

    let branchesExtractor = CodesExtractor.builder()
        .withInputName("FIRST_BRANCH")
        .withInputName("SECOND_BRANCH")
        .withJoin("\n}{\n  ")
        .withAppend("\n}")
        .build()

    return [conditionExtractor(block)[0] + branchesExtractor(block)[0], scafiGenerator.ORDER_FUNCTION_CALL]
}

scafiGenerator['mux'] = scafiGenerator['branch']; // Same names and same code

// #endregion


// #region Utilities

scafiGenerator['mid'] = (block) => ["mid", scafiGenerator.ORDER_FUNCTION_CALL];

scafiGenerator['distance_to'] = CodeExtractor.builder()
    .withInputName("SRC")
    .withPrepend("distanceTo(")
    .withAppend(")")
    .build()

scafiGenerator['channel'] = CodesExtractor.builder()
    .withInputName("SOURCE")
    .withInputName("TARGET")
    .withInputName("WIDTH")
    .withPrepend("channel(")
    .withAppend(")")
    .withJoin(", ")
    .build()

scafiGenerator['distance_between'] = CodesExtractor.builder()
    .withInputName("SOURCE")
    .withInputName("TARGET")
    .withPrepend("distanceBetween(")
    .withAppend(")")
    .withJoin(", ")
    .build()

// #endregion


// #region Sensors

scafiGenerator['sense'] = (block) => {
    const type = Blockly.ScaFi.valueToCode(block, "TYPE", scafiGenerator.ORDER_NONE);
    const name = Blockly.ScaFi.valueToCode(block, "SENSOR_NAME", scafiGenerator.ORDER_NONE);
    const code = `sense[${type}](${name})`;
    return [code, scafiGenerator.ORDER_FUNCTION_CALL];
}

// #endregion


// #region Actuators

let ledAllToExtractor = CodeExtractor.builder()
    .withInputName("COLOR")
    .withPrepend("ledAll to ")
    .build();
scafiGenerator['led_all_to'] = ledAllToExtractor

// #endregion


// #region Operators

scafiGenerator['equals'] = CodesExtractor.builder()
    .withInputName("LEFT")
    .withInputName("RIGHT")
    .withJoin(" == ")
    .build()

scafiGenerator['boolean_operation'] = (block) => CodesExtractor.builder()
    .withInputName("LEFT")
    .withInputName("RIGHT")
    .withJoin(" " + block.getFieldValue("OPERATOR") + " ")
    .build()(block)

scafiGenerator['ternary_operation'] = (block) => {
    let condition = Blockly.ScaFi.valueToCode(block, "CONDITION", scafiGenerator.ORDER_NONE);
    let then = Blockly.ScaFi.valueToCode(block, "THEN", scafiGenerator.ORDER_NONE);
    let else_ = Blockly.ScaFi.valueToCode(block, "ELSE", scafiGenerator.ORDER_NONE);
    return [`${condition} ? ${then} : ${else_}`, scafiGenerator.ORDER_NONE];
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

// #endregion


// #region Values

let valueExtractor = FieldExtractor.builder()
    .withFieldName("VALUE")
    .build();
scafiGenerator['integer_value'] = valueExtractor
scafiGenerator['double_value'] = valueExtractor
scafiGenerator['boolean_value'] = valueExtractor

let stringValueExtractor = FieldExtractor.builder()
    .withFieldName("VALUE")
    .withPrepend('"')
    .withAppend('"')
    .build()
scafiGenerator['string_value'] = stringValueExtractor
scafiGenerator['color_value'] = stringValueExtractor

let tupleExtractor = CodesExtractor.builder()
    .withInputName("VALUE_1")
    .withInputName("VALUE_2")
    .withJoin(", ")
    .withPrepend("(")
    .withAppend(")")
    .build();
scafiGenerator['tuple_value'] = tupleExtractor;

let randomValueExtractor = CodeExtractor.builder()
    .withInputName("MAX")
    .withPrepend("Random.nextInt(")
    .withAppend(")")
    .build();
scafiGenerator['random_value'] = randomValueExtractor

let randomValueBetweenExtractor = CodesExtractor.builder()
    .withInputName("MIN")
    .withInputName("MAX")
    .withJoin(", ")
    .withPrepend("Random.between(")
    .withAppend(")")
    .build();
scafiGenerator['random_value_between'] = randomValueBetweenExtractor

// #endregion


// #region Types

let classExtractor =
    FieldExtractor.builder()
    .withFieldName("NAME")
    .build();
scafiGenerator['class_integer'] = classExtractor;
scafiGenerator['class_double'] = classExtractor
scafiGenerator['class_boolean'] = classExtractor
scafiGenerator['class_string'] = classExtractor
scafiGenerator['class_other'] = classExtractor

// #endregion


// #region Functions

let returnExtractor = CodeExtractor.builder()
    .withInputName("RETURN")
    .build()
scafiGenerator['return'] = (block) => returnExtractor(block)[0]

scafiGenerator['function'] = (block) => {
    let def = block.getFieldValue('FUNCTION_NAME');
    let code = Blockly.ScaFi.statementToCode(block, 'BODY');
    return `def ${def}:\n${code}\n`;
}
scafiGenerator['function_1_param'] = (block) => {
    let def = block.getFieldValue('FUNCTION_NAME');
    let param = block.getFieldValue('PARAM_NAME');
    let type = Blockly.ScaFi.valueToCode(block, 'PARAM_TYPE', scafiGenerator.ORDER_NONE);
    let code = Blockly.ScaFi.statementToCode(block, 'BODY')
    return `def ${def}(${param}: ${type}):\n${code}\n`;
}
scafiGenerator['function_2_params'] = (block) => {}
scafiGenerator['function_3_params'] = (block) => {}

scafiGenerator['call_function'] = scafiGenerator['getter'] // same function

scafiGenerator['call_function_1_param'] = (block) => {
    let name = block.getFieldValue('NAME');
    let param = Blockly.ScaFi.valueToCode(block, 'PARAM', scafiGenerator.ORDER_NONE);
    return [`${name}(${param})`, scafiGenerator.ORDER_FUNCTION_CALL];
}
scafiGenerator['call_function_2_param'] = (block) => {
    let name = block.getFieldValue('NAME');
    let param = Blockly.ScaFi.valueToCode(block, 'PARAM_1', scafiGenerator.ORDER_NONE);
    let param2 = Blockly.ScaFi.valueToCode(block, 'PARAM_2', scafiGenerator.ORDER_NONE);
    return `${name}(${param}, ${param2})`;
}
scafiGenerator['call_function_3_param'] = (block) => {
    let name = block.getFieldValue('NAME');
    let param = Blockly.ScaFi.valueToCode(block, 'PARAM_1', scafiGenerator.ORDER_NONE);
    let param2 = Blockly.ScaFi.valueToCode(block, 'PARAM_2', scafiGenerator.ORDER_NONE);
    let param3 = Blockly.ScaFi.valueToCode(block, 'PARAM_3', scafiGenerator.ORDER_NONE);
    return `${name}(${param}, ${param2}, ${param3})`;
}

// #endregion


// #region Definitions

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
    code += " = " + Blockly.ScaFi.valueToCode(block, "VALUE", scafiGenerator.ORDER_NONE);
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
    code += " = " + Blockly.ScaFi.valueToCode(block, "VALUE", scafiGenerator.ORDER_NONE);
    return code;
}
scafiGenerator['var'] = function(block) {
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
    code += " = " + Blockly.ScaFi.valueToCode(block, "VALUE", scafiGenerator.ORDER_NONE);
    return code;
}

// #endregion


// GETTER
let getterExtractor = FieldExtractor.builder().withFieldName("NAME").build();
scafiGenerator['getter'] = getterExtractor




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