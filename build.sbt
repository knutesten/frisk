name := "lunsjtavle-scala"

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies += "org.apache.commons" % "commons-pool2" % "2.3"

libraryDependencies += "org.apache.commons" % "commons-dbcp2" % "2.0.1"

libraryDependencies += "com.h2database" % "h2" % "1.4.185"

libraryDependencies += "com.typesafe.slick" %% "slick" % "2.1.0"

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.5"

libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.2"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray" %%  "spray-json" % "1.3.1",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV
  )
}

Revolver.settings
