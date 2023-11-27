name := "AntBookProject"

lazy val commonSettings = Seq {
  scalaVersion := "2.13.3"
}

lazy val root = (project in file("."))
  .aggregate(
    p21
  )
  .settings(
    name := "ant-book",
    version := "1.0"
  )

lazy val p21 = (project in file("p21-triangle"))
  .settings(commonSettings: _*)
  .settings(
    name := "p21-triangle",
    version := "2020.09.06.01"
  )
