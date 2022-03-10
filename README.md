[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]


<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/alemazzo/blockly2scafi">
    <img src="https://github.com/alemazzo/blockly2scafi/blob/master/blockly2scafi.png" alt="Logo" width="300" height="300">
  </a>

  <h3 align="center">blockly2scafi</h3>

  <p align="center">
    A <a href="https://developers.google.com/blockly/">Blockly</a> environment developed in <a href="http://www.scala-js.org/">Scala.js</a> and Javascript with a custom code generator for <a href="https://scafi.github.io/">ScaFi</a>.
    <br />
    <a href="https://alemazzo.github.io/blockly2scafi/"><strong>Try it in Github pages</strong></a>
    <br />
    <br />
  </p>
</p>

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

#### Main folder
- **[index.html](src/main/resources/index.html)** contains the layout of the web page of blockly2scafi, styled by the css file **[main.css](src/main/resources/main.css)**.
- **[blockly2scafi.js](src/main/resources/blockly2scafi.js)** initializes the blocky workspace and toolbox.
- **[scafi_generator.js](src/main/resources/scafi_generator.js)** builds the scafi code generator that translates the Blocks to valid and formatted Scafi Code.

#### Config folder
- **[config/library.xml](src/main/resources/config/library.xml)** is not used by blockly2scafi, it's the exported [Blockly Developer Tools](https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#) library.
- **[config/library.json](src/main/resources/config/library.json)** contains the exported blocks definitions in JSON format. It's used to create the workspace.
- **[config/toolbox.xml](src/main/resources/config/toolbox.xml)** contains the exported toolbox definition in XML format.
- **[config/initialWorkspace.xml](src/main/resources/config/initialWorkspace.xml)** contains the initial workspace structure in XML format.

## How to add or edit blocks
1. Open [Blockly Developer Tools](https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#).
2. Click on *Import Block Library* and upload the file [config/library.xml](src/main/resources/config/library.xml).
3. Define or edit the shape of the blocks using the tool.
4. Go to *Block Exporter*, select *All Stored in Block Library* and export only the *Block Definitions* overwriting the [config/library.json](src/main/resources/config/library.json) file. 
5. Create or edit the code generator function of the block in [scafi_generator.js](src/main/resources/scafi_generator.js).
6. Remember to download the block library xml from [Blockly Developer Tools](https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#) and save it in [config/library.xml](src/main/resources/config/library.xml).


## How to add or edit toolbox categories
1. Open [Blockly Developer Tools](https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#).
2. Click on *Import Block Library* and upload the file [config/library.xml](src/main/resources/config/library.xml).
3. Go to *Workspace Factory* section.
4. Click on *Load to Edit* and select the [config/toolbox.xml](src/main/resources/config/toolbox.xml).
5. Define or edit the categories using the tool.
6. Click *Export*, select *Toolbox* and overwrite the [config/toolbox.xml](src/main/resources/config/toolbox.xml) file.
7. Add `custom="Functions"` attribute to the `Functions` category in [config/toolbox.xml](src/main/resources/config/toolbox.xml).
8. Add `custom="Definitions"` attribute to the `Definitions` category in [config/toolbox.xml](src/main/resources/config/toolbox.xml).





<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/alemazzo/blockly2scafi.svg?style=flat-square
[contributors-url]: https://github.com/alemazzo/blockly2scafi/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/alemazzo/blockly2scafi.svg?style=flat-square
[forks-url]: https://github.com/alemazzo/blockly2scafi/network/members
[stars-shield]: https://img.shields.io/github/stars/alemazzo/blockly2scafi.svg?style=flat-square
[stars-url]: https://github.com/alemazzo/blockly2scafi/stargazers
[issues-shield]: https://img.shields.io/github/issues/alemazzo/blockly2scafi.svg?style=flat-square
[issues-url]: https://github.com/alemazzo/blockly2scafi/issues
[license-shield]: https://img.shields.io/github/license/alemazzo/blockly2scafi.svg?style=flat-square
[license-url]: https://github.com/alemazzo/blockly2scafi/blob/master/LICENSE.txt
[product-screenshot]: images/screenshot.png