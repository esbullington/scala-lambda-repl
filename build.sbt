name := """lambda"""

version := "1.0"

scalaVersion := "2.12.0"

scalacOptions += "-deprecation"

//fork in run := true

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"
