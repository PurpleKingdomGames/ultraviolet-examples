package sitegenerator

class ProjectTreeTests extends munit.FunSuite:

  test("pathToProjectTree") {
    val path = List("a", "b", "c")

    val actual =
      ProjectTree.pathToProjectTree(path)

    val expected =
      ProjectTree.Branch(
        "a",
        List(
          ProjectTree.Branch(
            "b",
            List(
              ProjectTree.Leaf("c")
            )
          )
        )
      )

    assertEquals(actual, expected)
  }

  test("combineTrees") {

    val pathA = List("a", "b", "c", "d")
    val pathB = List("a", "b", "c", "e")
    val pathC = List("a", "b", "f", "g")
    val pathD = List("a", "b", "h")

    val trees = List(
      ProjectTree.pathToProjectTree(pathA),
      ProjectTree.pathToProjectTree(pathB),
      ProjectTree.pathToProjectTree(pathC),
      ProjectTree.pathToProjectTree(pathD)
    )

    val actual =
      ProjectTree.combineTrees(trees)

    val expected =
      List(
        ProjectTree.Branch(
          "a",
          List(
            ProjectTree.Branch(
              "b",
              List(
                ProjectTree.Branch(
                  "c",
                  List(
                    ProjectTree.Leaf("d"),
                    ProjectTree.Leaf("e")
                  )
                ),
                ProjectTree.Branch(
                  "f",
                  List(
                    ProjectTree.Leaf("g")
                  )
                ),
                ProjectTree.Leaf("h")
              )
            )
          )
        )
      )

    assertEquals(actual, expected)
  }

  test("combine") {
    val paths =
      List(
        // "shaders.fragment.basics.colours",
        "fragment.sdf.circle",
        "fragment.shapes.doughnut",
        "fragment.shapes.metaballs"
      ).map(ProjectTree.stringToProjectTree)

    val actual =
      ProjectTree.combineTrees(paths)

    val expected =
      List(
        ProjectTree.Branch(
          "fragment",
          List(
            ProjectTree.Branch(
              "sdf",
              List(
                ProjectTree.Leaf("circle")
              )
            ),
            ProjectTree.Branch(
              "shapes",
              List(
                ProjectTree.Leaf("doughnut"),
                ProjectTree.Leaf("metaballs")
              )
            )
          )
        )
      )

    assertEquals(actual, expected)
  }

  test("real example") {
    val paths =
      List(
        "shaders.fragment.basics.colours",
        "shaders.fragment.basics.minimal",
        "shaders.fragment.demos.campfire",
        "shaders.fragment.demos.glowing-star",
        "shaders.fragment.demos.pulsingbox",
        "shaders.fragment.noise.cellular-noise",
        "shaders.fragment.noise.classic-perlin-noise",
        "shaders.fragment.noise.gradient-noise",
        "shaders.fragment.noise.simplex-noise",
        "shaders.fragment.noise.white-noise",
        "shaders.fragment.patterns.grid",
        "shaders.fragment.patterns.rainbow",
        "shaders.fragment.patterns.simple-voronoi",
        "shaders.fragment.patterns.stripes",
        "shaders.fragment.patterns.wavy-stripes",
        "shaders.fragment.sdf.circle",
        "shaders.fragment.sdf.square",
        "shaders.fragment.sdf.star",
        "shaders.fragment.shapes.doughnut",
        "shaders.fragment.shapes.metaballs",
        "shaders.language-reference.imports",
        "shaders.vertex.minimal"
      ).map(ProjectTree.stringToProjectTree)

    val actual =
      ProjectTree.combineTrees(paths)

    val expected =
      List(
        ProjectTree.Branch(
          "shaders",
          List(
            ProjectTree.Branch(
              "fragment",
              List(
                ProjectTree.Branch(
                  "basics",
                  List(
                    ProjectTree.Leaf("colours"),
                    ProjectTree.Leaf("minimal")
                  )
                ),
                ProjectTree.Branch(
                  "demos",
                  List(
                    ProjectTree.Leaf("campfire"),
                    ProjectTree.Leaf("glowing-star"),
                    ProjectTree.Leaf("pulsingbox")
                  )
                ),
                ProjectTree.Branch(
                  "noise",
                  List(
                    ProjectTree.Leaf("cellular-noise"),
                    ProjectTree.Leaf("classic-perlin-noise"),
                    ProjectTree.Leaf("gradient-noise"),
                    ProjectTree.Leaf("simplex-noise"),
                    ProjectTree.Leaf("white-noise")
                  )
                ),
                ProjectTree.Branch(
                  "patterns",
                  List(
                    ProjectTree.Leaf("grid"),
                    ProjectTree.Leaf("rainbow"),
                    ProjectTree.Leaf("simple-voronoi"),
                    ProjectTree.Leaf("stripes"),
                    ProjectTree.Leaf("wavy-stripes")
                  )
                ),
                ProjectTree.Branch(
                  "sdf",
                  List(
                    ProjectTree.Leaf("circle"),
                    ProjectTree.Leaf("square"),
                    ProjectTree.Leaf("star")
                  )
                ),
                ProjectTree.Branch(
                  "shapes",
                  List(
                    ProjectTree.Leaf("doughnut"),
                    ProjectTree.Leaf("metaballs")
                  )
                )
              )
            ),
            ProjectTree.Branch(
              "language-reference",
              List(
                ProjectTree.Leaf("imports")
              )
            ),
            ProjectTree.Branch(
              "vertex",
              List(
                ProjectTree.Leaf("minimal")
              )
            )
          )
        )
      )

    assertEquals(actual, expected)
  }
