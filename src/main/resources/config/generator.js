Blockly.JavaScript['aggregate_program'] = function(block) {
    var statements_aggregate_program_main = Blockly.JavaScript.statementToCode(block, 'AGGREGATE_PROGRAM_MAIN');
    // TODO: Assemble JavaScript into code variable.
    var code = '...;\n';
    return code;
};

Blockly.JavaScript['output'] = function(block) {
    var value_output_value = Blockly.JavaScript.valueToCode(block, 'OUTPUT_VALUE', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = '...;\n';
    return code;
};

Blockly.JavaScript['string'] = function(block) {
    var text_string_value = block.getFieldValue('STRING_VALUE');
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['integer'] = function(block) {
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['boolean'] = function(block) {
    var dropdown_boolean_value = block.getFieldValue('BOOLEAN_VALUE');
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['sense'] = function(block) {
    var value_type = Blockly.JavaScript.valueToCode(block, 'TYPE', Blockly.JavaScript.ORDER_ATOMIC);
    var value_sensor_name = Blockly.JavaScript.valueToCode(block, 'SENSOR_NAME', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['tuple'] = function(block) {
    var value_value_1 = Blockly.JavaScript.valueToCode(block, 'VALUE_1', Blockly.JavaScript.ORDER_ATOMIC);
    var value_value_2 = Blockly.JavaScript.valueToCode(block, 'VALUE_2', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['mux'] = function(block) {
    var value_condition = Blockly.JavaScript.valueToCode(block, 'CONDITION', Blockly.JavaScript.ORDER_ATOMIC);
    var value_first_branch = Blockly.JavaScript.valueToCode(block, 'FIRST_BRANCH', Blockly.JavaScript.ORDER_ATOMIC);
    var value_second_branch = Blockly.JavaScript.valueToCode(block, 'SECOND_BRANCH', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['boolean_operation'] = function(block) {
    var value_first = Blockly.JavaScript.valueToCode(block, 'FIRST', Blockly.JavaScript.ORDER_ATOMIC);
    var dropdown_operator = block.getFieldValue('OPERATOR');
    var value_second = Blockly.JavaScript.valueToCode(block, 'SECOND', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['double'] = function(block) {
    var number_value = block.getFieldValue('VALUE');
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['getter'] = function(block) {
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['define'] = function(block) {
    var text_name = block.getFieldValue('NAME');
    var value_value = Blockly.JavaScript.valueToCode(block, 'VALUE', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = '...;\n';
    return code;
};

Blockly.JavaScript['distance_to'] = function(block) {
    var value_src = Blockly.JavaScript.valueToCode(block, 'SRC', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['channel'] = function(block) {
    var value_source = Blockly.JavaScript.valueToCode(block, 'SOURCE', Blockly.JavaScript.ORDER_ATOMIC);
    var value_target = Blockly.JavaScript.valueToCode(block, 'TARGET', Blockly.JavaScript.ORDER_ATOMIC);
    var value_width = Blockly.JavaScript.valueToCode(block, 'WIDTH', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['led_all_to'] = function(block) {
    var value_color = Blockly.JavaScript.valueToCode(block, 'COLOR', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['color'] = function(block) {
    var colour_color = block.getFieldValue('COLOR');
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['type'] = function(block) {
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['other_type'] = function(block) {
    var text_type = block.getFieldValue('TYPE');
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['distance_between'] = function(block) {
    var value_source = Blockly.JavaScript.valueToCode(block, 'SOURCE', Blockly.JavaScript.ORDER_ATOMIC);
    var value_target = Blockly.JavaScript.valueToCode(block, 'TARGET', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['number_compare'] = function(block) {
    var value_first = Blockly.JavaScript.valueToCode(block, 'FIRST', Blockly.JavaScript.ORDER_ATOMIC);
    var dropdown_operator = block.getFieldValue('OPERATOR');
    var value_second = Blockly.JavaScript.valueToCode(block, 'SECOND', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['number_operation'] = function(block) {
    var value_first = Blockly.JavaScript.valueToCode(block, 'FIRST', Blockly.JavaScript.ORDER_ATOMIC);
    var dropdown_operator = block.getFieldValue('OPERATOR');
    var value_second = Blockly.JavaScript.valueToCode(block, 'SECOND', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['random'] = function(block) {
    var value_min = Blockly.JavaScript.valueToCode(block, 'MIN', Blockly.JavaScript.ORDER_ATOMIC);
    var value_max = Blockly.JavaScript.valueToCode(block, 'MAX', Blockly.JavaScript.ORDER_ATOMIC);
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['mid'] = function(block) {
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['foldhood'] = function(block) {
    var value_start = Blockly.JavaScript.valueToCode(block, 'START', Blockly.JavaScript.ORDER_ATOMIC);
    var statements_aggregator = Blockly.JavaScript.statementToCode(block, 'AGGREGATOR');
    var statements_expression = Blockly.JavaScript.statementToCode(block, 'EXPRESSION');
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['foldhoodplus'] = function(block) {
    var value_start = Blockly.JavaScript.valueToCode(block, 'START', Blockly.JavaScript.ORDER_ATOMIC);
    var statements_aggregator = Blockly.JavaScript.statementToCode(block, 'AGGREGATOR');
    var statements_expression = Blockly.JavaScript.statementToCode(block, 'EXPRESSION');
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['nbr'] = function(block) {
    var statements_expression = Blockly.JavaScript.statementToCode(block, 'EXPRESSION');
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};

Blockly.JavaScript['rep'] = function(block) {
    var value_start = Blockly.JavaScript.valueToCode(block, 'START', Blockly.JavaScript.ORDER_ATOMIC);
    var variable_previous = Blockly.JavaScript.nameDB_.getName(block.getFieldValue('PREVIOUS'), Blockly.Variables.NAME_TYPE);
    var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
    // TODO: Assemble JavaScript into code variable.
    var code = '...';
    // TODO: Change ORDER_NONE to the correct strength.
    return [code, Blockly.JavaScript.ORDER_NONE];
};