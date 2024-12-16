ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "ScalixTP",
    libraryDependencies ++= Seq(
      "org.json4s" %% "json4s-native" % "4.1.0-M8"
    )
  )
