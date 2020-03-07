# Scala.js Demo


## Preparation

* add sbt-scalajs plugin to `project/plugins.sbt`
  > addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.0.0")

* add the following to `build.sbt`

* specify the `sbt` version under project/build.properties
  > sbt.version=1.3.7

```sbt
enablePlugins(ScalaJSPlugin)
// This is an application with a main method
scalaJSUseMainModuleInitializer := true
```

### enable Stack Trace in Node

>  npm install source-map-support

### Generate Javascript

> fastOptJS

