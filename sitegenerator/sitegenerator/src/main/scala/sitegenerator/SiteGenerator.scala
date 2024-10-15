package sitegenerator

object SiteGenerator:

  // TODO: Cli args for working directory, and where to link or not
  @main def run: Unit =
    val wd = os.pwd / os.up

    val projects = buildProjectList(wd)
    val trees = convertProjectListToTree(projects)

    println("Found projects:")
    println(trees.map(_.prettyPrint).mkString("\n"))

    println("Building demo site...")
    makeDemoSite(true, projects, wd)

    println("Done")

    // make(linkAll = true)

  def buildProjectList(wd: os.Path): List[String] =
    // Extract all sub-projects
    val findProjects = os
      .proc(
        "./mill",
        "resolve",
        "__.fullLinkJS"
      )
      .spawn(cwd = wd)

    val filterOutTestProjects = os
      .proc(
        "grep",
        "-v",
        "test"
      )
      .spawn(cwd = wd, stdin = findProjects.stdout)

    val cleanUpNames = os
      .proc(
        "sed",
        "s/.fullLinkJS//"
      )
      .spawn(cwd = wd, stdin = filterOutTestProjects.stdout)

    LazyList
      .continually(cleanUpNames.stdout.readLine())
      .takeWhile(_ != null)
      .toList

  def convertProjectListToTree(projectList: List[String]): List[ProjectTree] =
    val treeList = projectList.map(ProjectTree.stringToProjectTree)
    ProjectTree.combineTrees(treeList)

  // LinkAll is a flag to build all the shaders before generating the site
  def makeDemoSite(linkAll: Boolean, projectList: List[String], wd: os.Path) =
    // Build all the shaders
    if (linkAll) {
      projectList.foreach { pjt =>
        os.proc("./mill", s"$pjt.buildGameFull").call(cwd = wd)
      }
    }

    // Recreate the docs directory
    val docs = wd / "docs"
    os.remove.all(docs)
    os.makeDir.all(docs)

    // Generate relative paths
    val projectListRelPaths: List[os.RelPath] =
      projectList.map { p =>
        os.RelPath(p.replace(".", "/"))
      }

    // Copy all the built shaders into the right docs directory
    projectListRelPaths.foreach { p =>
      val outPath = (wd / "docs") / p
      os.makeDir.all(outPath)

      val buildDir = wd / "out" / p / "indigoBuildFull.dest"

      os.list(buildDir)
        .toList
        .filterNot { p =>
          p.last == "cordova.js" ||
          p.last == "indigo-support.js" ||
          p.last == "index.html"
        }
        .foreach { p =>
          os.copy(p, outPath / p.last)
        }

      // Write a custom index page
      os.write(outPath / "index.html", IndigoIndex.page(outPath.last))
    }

    // Build an index page with links to all the sub folders
    os.write(
      docs / "index.html",
      HomePage.page(projectListRelPaths.map(_.toString()))
    )
