# Motivation

This project is motivated from two needs:

1. The most pressing need is that GLSL tooling is patchy, and I'd like to have a much better shader writing experience both for myself and any other Scala devs whether they're writing shaders for [Indigo](https://indigoengine.io/), [ShaderToy](https://www.shadertoy.com/), or some other Scala front end web framework.
2. Indigo is currently locked into WebGL 2.0, and to move to other platforms or rendering technologies means having some way to abstract away from that. 

## Current Goals

Right now, the goal is an almost like-for-like experience of writing GLSL (for WebGL) in Scala 3, in all it's very specific procedural glory. It includes a few quality of life improvements such as anonymous functions and function composition, but nothing fancy for now. You can even write unit tests!

The library may ultimately diverge from GLSL, and who knows what sort of problems have been caused by using GLSL as a starting point, but replacing GLSL with Scala GLSL-flavored-shader-experience is the current goal.

It is _not_ a goal to be able to write arbitrary Scala and have it turned into GLSL. In other words this isn't a 'full' transpiler (like Scala.js), it's a useful cross-over subset of Scala and GLSL. As many GLSL language features as can sensibly be represented, and as much Scala as GLSL can be coerced into expressing.

Ultimately I'd like to be able to write Shaders in FP friendly Scala that can target more than just GLSL 300, but that is not necessary for Ultraviolet to be useful and fun.
