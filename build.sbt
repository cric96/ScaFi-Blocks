scalaVersion := "3.3.1"
version := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  // .enablePlugins(ScalaJSReflectionPlugin)
  .settings(
    name := "ScaFi-Blocks",
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test,
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.8.0",
    // libraryDependencies += "org.reflections" % "reflections" % "0.10.2"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision
