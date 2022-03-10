# blockly2scafi #

**blockly2scafi** is a [**Blockly**](https://developers.google.com/blockly/) environment developed
in [Scala.js](http://www.scala-js.org/) and Javascript with a custom code generator for [**ScaFi**](https://scafi.github.io/).

**Try it in <a href="https://alemazzo.github.io/blockly2scafi/" target="_blank">Github pages</a>.**

## Installation

- Compile optimized Scala.js with [SBT](https://www.scala-sbt.org/) :

```
sbt fullOptJS
```

- Open in browser the file [index.html](src/main/resources/index.html)


<!-- ROADMAP -->
## Roadmap

- [ ] Put return block by default in functions.
- [ ] Detect return type of functions.
- [ ] Refactor FOLD operations.
- [ ] Refactor sensors management.
- [ ] Built lambda constructs for REP and FOLD constructs.
- [ ] Implement `minHood` and `minHoodPlus`.
- [ ] Implement `if` construct.
- [ ] Implement `hsl` function.
- [ ] Implement `G2` function.
- [ ] Implement `C` function.
- [ ] Implement `S` function.
- [ ] Implement `nbrRange` function.
- [ ] Implement `broadcast` function.
- [ ] Implement modulo operation.


## Project structure
The Blockly environment setup and code generator are written in JavaScript in the [resource](src/main/resources) directory:
- **[index.html](src/main/resources/index.html)** contains the layout of the web page of blockly2scafi, styled by the css file **[main.css](src/main/resources/main.css)**.
- **[blockly2scafi.js](src/main/resources/blockly2scafi.js)** initializes the blocky workspace and defines the toolbox.
- **[blocks_library.js](src/main/resources/blocks_library.js)** contains the json definition of the blocks and set up the event listeners used to update the dynamic output type of some blocks.
- **[scafi_generator.js](src/main/resources/scafi_generator.js)** builds the scafi code generator that translates the Blocks to valid and formatted Scafi Code.
- **[blocks_library.xml](src/main/resources/config/library.xml)** this file is not used by blockly2scafi, it's the exported [Blockly Developer Tools](https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#) library.

## How to add or edit blocks
1. Open [Blockly Developer Tools](https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#).
2. Click on *Import Block Library* and upload the file [config/library.xml](src/main/resources/config/library.xml).
3. Define or edit the shape of the blocks using the tool.
4. Go to *Block Exporter*, select *All Stored in Block Library* and export only the *Block Definitions* overwriting the [config/library.json](src/main/resources/config/library.json) file. 
5. Create or edit the code generator function of the block in [scafi_generator.js](src/main/resources/scafi_generator.js).
6. Remember to download the block library xml from [Blockly Developer Tools](https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#) and save it in [config/library.xml](src/main/resources/config/library.xml).


## How to add or edit toolbox categories
1. Go to *Workspace Factory* section.
2. Click on *Load to Edit* and select the [config/toolbox.xml](src/main/resources/config/toolbox.xml).
3. Define or edit the categories using the tool.
4. Click *Export*, select *Toolbox* and overwrite the [config/toolbox.xml](src/main/resources/config/toolbox.xml) file.
5. Add `custom="Functions"` attribute to the `Functions` category in [config/toolbox.xml](src/main/resources/config/toolbox.xml).
6. Add `custom="Definitions"` attribute to the `Definitions` category in [config/toolbox.xml](src/main/resources/config/toolbox.xml).


