ThisBuild / scalaVersion     := "2.12.15"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "duyet"

lazy val root = (project in file("."))
  .settings(
    name := "spark-example",
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-sql" % "3.3.2" % "provided",
      "org.scalatest" %% "scalatest" % "3.2.15" % Test,
    ),
    testOptions += Tests.Argument("-oD"),
    Compile / run := (Compile / runMain).dependsOn(Test / test).evaluated
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
