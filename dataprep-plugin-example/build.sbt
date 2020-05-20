organization := "org.dcp.example-plugin"
version := "1.0.0"
scalaVersion := "2.12.10"


unmanagedBase := new java.io.File("/Users/ravi/dev/unifing/services/executor/executor-service/jars")

mainClass in assembly := Some("org.dcp.plugin.example.ExamplePlugin")
mainClass in (Compile, packageBin) := Some("org.dcp.plugin.example.ExamplePlugin")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

libraryDependencies += "org.scalatest" %% "scalatest"         % "3.0.3"  % Test  
