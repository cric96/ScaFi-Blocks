Blockly.createBlockly2ScafiWorkspace = function(elt) {

    function loadConfigurationFromXmlFile(xmlFilePath) {
        // Make a sync get request with XHTTP
        const xml = new XMLHttpRequest();
        xml.open("GET", xmlFilePath, false);
        xml.send();
        return new XMLSerializer().serializeToString(xml.responseXML.children[0]);
    }

    const toolbox = loadConfigurationFromXmlFile('config/toolbox.xml');
    const initialWorkspace = loadConfigurationFromXmlFile('config/initialWorkspace.xml');


    const workspace = Blockly.inject(elt, {
        toolbox: toolbox
    });

    const definitionsDynamicCategoryCallback = function(workspace) {
        const blockList = [];
        blockList.push({
            'kind': 'block',
            'type': 'define',
        });
        blockList.push({
            'kind': 'block',
            'type': 'val',
        });
        const defineBlocks = workspace.getBlocksByType('define').concat(workspace.getBlocksByType('val'));
        for (const defineBlock of defineBlocks) {
            const defName = defineBlock.getFieldValue('NAME');
            blockList.push({
                'kind': 'block',
                'type': 'getter',
                'fields': {
                    'NAME': defName,
                },
                'data': {
                    'defineBlockId': defineBlock.id,
                }
            });
        }
        return blockList;
    };

    //workspace.registerToolboxCategoryCallback('DEFINITIONS', definitionsDynamicCategoryCallback);

    Blockly.Xml.domToWorkspace(Blockly.Xml.textToDom(initialWorkspace), workspace);

    workspace.addChangeListener(Blockly.Events.disableOrphans); //Disable all blocks outside the main block

    return workspace;
}