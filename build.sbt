name := "scalajs-demo"

version := "0.1"

scalaVersion := "2.13.1"

resolvers ++= Seq(
  "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"
)

enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaJSBundlerPlugin)

scalaJSUseMainModuleInitializer := true

libraryDependencies ++= Seq(
  "org.scala-js"      %%%     "scalajs-dom"     %   "1.0.0",
)

npmDependencies in Compile ++= Seq(
  "jquery" -> "3.2.1"
)

mainClass in Compile  := Some("com.sghaida.demo.intro")



