val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "pp-assignment-1",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0-M10" % Test,

    cancelable in Global := true
  )
