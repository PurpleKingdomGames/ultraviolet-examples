package sitegenerator

import scala.annotation.tailrec

enum ProjectTree:
  case Branch(name: String, children: List[ProjectTree])
  case Leaf(name: String)
  case Empty

  def prettyPrint(level: Int): String =
    this match
      case ProjectTree.Branch(name, children) =>
        val indent = "  " * level
        val childStr = children.map(_.prettyPrint(level + 1)).mkString("\n")
        s"$indent$name\n$childStr"

      case ProjectTree.Leaf(name) =>
        val indent = "  " * level
        s"$indent$name"

      case ProjectTree.Empty =>
        ""

  def prettyPrint: String =
    prettyPrint(0)

  def isEmpty: Boolean =
    this match
      case Branch(name, children) => false
      case Leaf(name)             => false
      case Empty                  => true

  def hasName(name: String): Boolean =
    this match
      case Branch(n, _) => n == name
      case Leaf(n)      => n == name
      case Empty        => false

object ProjectTree:

  def pathToProjectTree(path: List[String]): ProjectTree =
    path match
      case Nil =>
        ProjectTree.Empty

      case head :: Nil =>
        ProjectTree.Leaf(head)

      case head :: tail =>
        ProjectTree.Branch(head, List(pathToProjectTree(tail)))

  def stringToProjectTree(path: String): ProjectTree =
    pathToProjectTree(path.split("""\.""").toList)

  def combineTrees(trees: List[ProjectTree]): List[ProjectTree] =
    @tailrec
    def rec(
        remaining: List[ProjectTree],
        acc: List[ProjectTree]
    ): List[ProjectTree] =
      remaining match
        case Nil =>
          acc

        case ProjectTree.Empty :: pts =>
          rec(pts, acc)

        case (pt @ ProjectTree.Leaf(name)) :: pts if acc.isEmpty =>
          rec(pts, List(pt))

        case (pt @ ProjectTree.Leaf(name)) :: pts =>
          rec(pts, acc :+ pt)

        case (pt @ ProjectTree.Branch(name, _)) :: pts if acc.isEmpty =>
          rec(pts, List(pt))

        case (pt @ ProjectTree.Branch(name, _)) :: pts =>
          // The expected case.
          val res =
            acc.find(_.hasName(name)) match
              case None =>
                acc :+ pt

              case Some(existing) =>
                acc.filterNot(_.hasName(name)) ++ zipTrees(existing, pt)

          rec(pts, res)

    rec(trees, Nil)

  def zipTrees(a: ProjectTree, b: ProjectTree): List[ProjectTree] =
    (a, b) match
      case (
            ProjectTree.Branch(nameA, childrenA),
            ProjectTree.Branch(nameB, childrenB)
          ) if nameA == nameB =>
        List(ProjectTree.Branch(nameA, combineTrees(childrenA ++ childrenB)))

      case (
            b1 @ ProjectTree.Branch(nameA, _),
            b2 @ ProjectTree.Branch(nameB, _)
          ) =>
        List(b1, b2)

      case (ProjectTree.Leaf(nameA), ProjectTree.Leaf(nameB))
          if nameA == nameB =>
        List(ProjectTree.Leaf(nameA))

      case (ProjectTree.Branch(nameA, childrenA), ProjectTree.Leaf(nameB)) =>
        List(ProjectTree.Branch(nameA, childrenA :+ ProjectTree.Leaf(nameB)))

      case (ProjectTree.Leaf(nameA), ProjectTree.Branch(nameB, childrenB)) =>
        List(ProjectTree.Branch(nameB, ProjectTree.Leaf(nameA) :: childrenB))

      case (l1 @ ProjectTree.Leaf(nameA), l2 @ ProjectTree.Leaf(nameB)) =>
        List(l1, l2)

      case (pt, ProjectTree.Empty) =>
        List(pt)

      case (ProjectTree.Empty, pt) =>
        List(pt)
