import Dependencies._

ThisBuild / scalaVersion     := "2.12.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "MatteoCerioni"
ThisBuild / organizationName := "MatteoCerioni"

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  //.enablePlugins(ScalaJSReflectionPlugin)
  .settings(
    name := "blockly2scafi",
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.0.0",
    libraryDependencies += "org.reflections" % "reflections" % "0.10.2"

  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
