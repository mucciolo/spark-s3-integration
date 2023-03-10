ThisBuild / version := "1.0.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.10"

val SparkVersion = "3.3.2"
val HadoopVersion = "3.3.4"
val ScalaTestVersion = "3.2.15"

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    name := "spark-s3-integration",
    idePackagePrefix := Some("com.mucciolo"),
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % SparkVersion,
      "org.apache.hadoop" % "hadoop-aws" % HadoopVersion,
      "org.scalatest" %% "scalatest" % ScalaTestVersion % "test, it"
    ),
    Defaults.itSettings,
    fork := true,
    IntegrationTest / envVars := Map(
      "AWS_CREDENTIAL_PROFILES_FILE" -> "src/it/resources/aws/credentials"
    )
  )