package purpledoc

import mainargs.{main, arg, ParserForMethods, Flag}

object Main:

  @main
  def run(
      @arg(short = 'i', doc = "Path to the project root")
      input: String,
      @arg(
        doc =
          "Boolean flag to specify whether or not to link all Scala.js projects, default is true"
      )
      nolink: Flag
  ) =
    go(input, nolink.value)

  def main(args: Array[String]): Unit = ParserForMethods(this).runOrExit(args)

  def go(input: String, noLink: Boolean): Unit =
    val wd =
      if input.startsWith(".") then os.pwd / os.RelPath(input)
      else os.Path(input)

    val projectList = MillProjectLister.buildProjectList(wd)
    val trees =
      ProjectTree.combineTrees(projectList.map(ProjectTree.stringToProjectTree))

    println("Found projects:")
    println(trees.map(_.prettyPrint).mkString("\n"))

    println("Building demo site...")
    SiteGenerator.makeDemoSite(!noLink, projectList, wd)

    println("Done")
