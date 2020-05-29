val swing = "org.scala-lang.modules" %% "scala-swing" % "2.0.1"
val parser = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"

lazy val root = (project in file(".")).settings(
  name := "TowerDefProj",
  libraryDependencies += swing,
  libraryDependencies += parser
)
