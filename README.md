[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]


<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/alemazzo/blockly2scafi">
    <img src="https://github.com/alemazzo/blockly2scafi/blob/master/scafiblocks.png" alt="Logo" width="300" height="300">
  </a>

<h3 align="center">blockly2scafi</h3>

  <p align="center">
    A <a href="https://developers.google.com/blockly/">Blockly</a> environment developed in <a href="http://www.scala-js.org/">Scala.js</a> with a custom code generator for <a href="https://scafi.github.io/">ScaFi</a>.
    <br />
    <a href="https://alemazzo.github.io/blockly2scafi/"><strong>Try it in Github pages</strong></a>
    <br />
    <br />
  </p>
</p>

<!-- TABLE OF CONTENTS -->

## Table of Contents

* [About the Project](#about-the-project)
    * [Built With](#built-with)
* [Getting Started](#getting-started)
    * [Prerequisites](#prerequisites)
    * [Installation](#installation)
* [Usage](#usage)
* [Roadmap](#roadmap)
* [Contributing](#contributing)
    * [Project Structure](#project-structure)
    * [Add or edit blocks](#how-to-add-or-edit-blocks)
    * [Add or edit toolbox](#how-to-add-or-edit-toolbox-categories)
* [License](#license)
* [Contact](#contact)
* [Acknowledgements](#acknowledgements)

<!-- ABOUT THE PROJECT -->

## About The Project

blockly2scafi is a Blockly environment developed in Scala.js with a custom code generator for ScaFi.

### Built With

* [Blockly](https://developers.google.com/blockly)
* [Scala.js](https://www.scala-js.org/)
* [Javascript](https://www.javascript.com/)

<!-- GETTING STARTED -->

## Getting Started

### Prerequisites

- Install [sbt](https://www.scala-sbt.org/)

### Installation

- Compile optimized Scala.js with [SBT](https://www.scala-sbt.org/) :

```sh
sbt fullOptJS
```

- Open in browser the file [index.html](src/main/resources/index.html)

<!-- USAGE EXAMPLES -->

## Usage

Drag and drop the blocks from the relative categories in the toolbox to create an aggregate program that will be display
on the right section.

<!-- ROADMAP -->

## Roadmap

- [ ] Implement `G2` function.
- [ ] Implement `C` function.
- [ ] Implement `S` function.
- [ ] Implement `broadcast` function.

## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

### Project structure

#### Config folder

- **[config/library.xml](src/main/resources/config/library.xml)** is not used by blockly2scafi, it's the
  exported [Blockly Developer Tools](https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#) library.
- **[config/library.json](src/main/resources/config/library.json)** contains the exported blocks definitions in JSON
  format. It's used to create the workspace.
- **[config/toolbox.xml](src/main/resources/config/toolbox.xml)** contains the exported toolbox definition in XML
  format.
- **[config/initialWorkspace.xml](src/main/resources/config/initialWorkspace.xml)** contains the initial workspace
  structure in XML format.

#### Resource folder

- **[index.html](src/main/resources/index.html)** contains the layout of the web page of blockly2scafi, styled by the
  css file **[main.css](src/main/resources/main.css)**.
- **[blockly2scafi.js](src/main/resources/scafi-blocks-utils.js)** implements some of the facade methods used by Scala to
  initializes the blocky workspace and also for generating the ScaFi code.

### How to add or edit blocks

1. Open [Blockly Developer Tools](https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#).
2. Click on *Import Block Library* and upload the file [config/library.xml](src/main/resources/config/library.xml).
3. Define or edit the shape of the blocks using the tool.
4. Go to *Block Exporter*, select *All Stored in Block Library* and export only the *Block Definitions* overwriting
   the [config/library.json](src/main/resources/config/library.json) file.
5. Create or edit the code generator function of the block in the Scala code.
6. Remember to download the block library xml
   from [Blockly Developer Tools](https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#) and save it
   in [config/library.xml](src/main/resources/config/library.xml).

### How to add or edit toolbox categories

1. Open [Blockly Developer Tools](https://blockly-demo.appspot.com/static/demos/blockfactory/index.html#).
2. Click on *Import Block Library* and upload the file [config/library.xml](src/main/resources/config/library.xml).
3. Go to *Workspace Factory* section.
4. Click on *Load to Edit* and select the [config/toolbox.xml](src/main/resources/config/toolbox.xml).
5. Define or edit the categories using the tool.
6. Click *Export*, select *Toolbox* and overwrite the [config/toolbox.xml](src/main/resources/config/toolbox.xml) file.
7. Add `custom="Functions"` attribute to the `Functions` category
   in [config/toolbox.xml](src/main/resources/config/toolbox.xml).
8. Add `custom="Definitions"` attribute to the `Definitions` category
   in [config/toolbox.xml](src/main/resources/config/toolbox.xml).

<!-- LICENSE -->

## License

Distributed under the MIT License. See [LICENSE](https://github.com/alemazzo/blockly2scafi/blob/main/LICENSE) for more
information.

<!-- CONTACT -->

## Contact

Project Link: [https://github.com/alemazzo/blockly2scafi](https://github.com/alemazzo/blockly2scafi/)

<!-- ACKNOWLEDGEMENTS -->

## Acknowledgements

* [Alessandro Mazzoli](https://www.linkedin.com/in/alessandro-mazzoli-009868140/)

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

[license-url]: https://github.com/alemazzo/blockly2scafi/blob/master/LICENSE.
