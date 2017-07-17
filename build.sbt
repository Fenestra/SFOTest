name := """SFOTest"""

version := "1.0-SNAPSHOT"

//lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.5"
//scalaVersion := "2.11.0"

//libraryDependencies ++= Seq(
//  jdbc,
//  cache,
//  ws,
//  "mysql" % "mysql-connector-java" % "5.1.34",
//  "com.typesafe.play" %% "anorm" % "2.5.0",
//  "org.scalactic" %% "scalactic" % "3.0.1",
//  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
//  "org.scalatest" %% "scalatest" % "1.9.1" % "test"
//)
libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"
//libraryDependencies +=  "org.scalactic" %% "scalactic" % "3.0.1"
//libraryDependencies +=  "org.scalatest" %% "scalatest" % "3.0.1" % "test"

