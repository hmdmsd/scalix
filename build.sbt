ThisBuild / scalaVersion := "3.3.4"

Compile / scalaSource := baseDirectory.value / "src/main/scala"
Test / scalaSource := baseDirectory.value / "src/test/scala"

lazy val root = (project in file("."))
  .settings(
    name := "ScalixTP",
    version := "0.1.0",

    // Dependencies
    libraryDependencies ++= Seq(
      "org.json4s" %% "json4s-native" % "4.1.0-M8",           // JSON parsing library
      "org.scalatest" %% "scalatest" % "3.2.17" % Test        // ScalaTest for unit testing
    ),

    // Compiler options
    scalacOptions ++= Seq(
      "-deprecation",      // Emit warning for usage of deprecated APIs
      "-feature",          // Emit warning for misused features
      "-unchecked",        // Enable additional compiler checks
      "-encoding", "utf8"  // Specify character encoding
    )
  )
