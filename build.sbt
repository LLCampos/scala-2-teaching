name := "scala-2-teaching"

version := "0.1"

scalaVersion := "2.13.7"

val circeVersion = "0.13.0"
val catsVersion = "2.6.1"
val WeaverVersion = "0.7.0"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-generic-extras",
  "io.circe" %% "circe-parser",
  "io.circe" %% "circe-refined"
).map(_ % circeVersion)

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsVersion,

  "com.lihaoyi" %% "utest" % "0.7.9" % "test",
  "com.disneystreaming" %% "weaver-cats" % WeaverVersion % "test",
  "com.disneystreaming" %% "weaver-specs2" % WeaverVersion % "test",
  "org.specs2" %% "specs2-core" % "4.10.6" % "test",
  "org.scalatest" %% "scalatest" % "3.2.7" % "test",
  "org.scalameta" %% "munit" % "0.7.25" % "test",
)

testFrameworks += new TestFramework("weaver.framework.CatsEffect")
testFrameworks += new TestFramework("utest.runner.Framework")
testFrameworks += new TestFramework("munit.Framework")
//testFrameworks -= TestFrameworks.ScalaTest
