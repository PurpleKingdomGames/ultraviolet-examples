# Ultraviolet

Ultraviolet is a Scala 3 to GLSL (versions 100 and 300) transpiler library built on top of Scala 3 inline macros.

Examples can be found in the [examples directory of this repo](https://github.com/PurpleKingdomGames/ultraviolet/tree/main/examples/). You can use Ultraviolet to generate GLSL shader code for [Indigo](https://github.com/PurpleKingdomGames/indigo), and also for [ShaderToy](https://www.shadertoy.com/).

## Installation

Check the repo for the latest release number, and substitue the `x.y.z` below accordingly.

sbt

```
libraryDependencies += "io.indigoengine" %%% "ultraviolet" % "x.y.z"
```

Mill

```
def ivyDeps = Agg(ivy"io.indigoengine::ultraviolet::x.y.z")
```

Scala-CLI

```
//> using lib "io.indigoengine::ultraviolet:x.y.z"
```

## TL;DR: What is a Shader / Ultraviolet / basic programmer intuition

If you've stumbled across this repo and have no idea what all this shader stuff is about:

1. A shader program is used to render graphics on a GPU.
2. ***Think of a tiny C-like program that runs for every pixel on the screen***.
3. Ultraviolet allows you to write those programs in Scala 3.
