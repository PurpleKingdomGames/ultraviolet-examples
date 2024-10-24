
# Example: Shadertoy

In this example, we'll recreate ShaderToy's default program in Ultraviolet.

This program can be run as a Scala-Cli script from [the examples](https://github.com/PurpleKingdomGames/ultraviolet/tree/main/examples/shadertoy) and the output pasted into [ShaderToy's editor](https://www.shadertoy.com/new). The code produced is almost the same as the default ShaderToy template, and when run, looks like this:

![ShaderToy's default shader example](/img/shadertoy_default.png)

Here's the code:

```scala
import ultraviolet.shadertoy.*
import ultraviolet.syntax.*

inline def image =
  Shader[ShaderToyEnv, Unit] { env =>
    def mainImage(fragColor: vec4, fragCoord: vec2): vec4 = {
      // Normalized pixel coordinates (from 0 to 1)
      val uv: vec2 = fragCoord / env.iResolution.xy

      // Time varying pixel color
      val col: vec3 = 0.5f + 0.5f * cos(env.iTime + uv.xyx + vec3(0.0f, 2.0f, 4.0f))

      // Output to screen
      vec4(col, 1.0f)
    }
  }
```

The body has comments describing it take directly from the shader toy version, but let's walk through the Ultraviolet parts:

1. First we import ultraviolet's syntax and shadertoy support.
2. Then we define an `inline def` (important!) to hold our shader.
3. The shader takes a `ShaderToyEnv` environment (that provides globally available data like `iTime`) and in this case returns `Unit`.
4. The body is a function of environment (`env`) to our shader definition.
5. ShaderToy requires a `mainImage` function that is usually `void` in it's return type and you have to assign the output color to a known variable. With Ultraviolet's shader toy support, we return a `vec4` and the library will re-write it to do the right thing.

For comparison, here is the GLSL version ShaderToy provides:

```glsl
void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    // Normalized pixel coordinates (from 0 to 1)
    vec2 uv = fragCoord/iResolution.xy;

    // Time varying pixel color
    vec3 col = 0.5 + 0.5*cos(iTime+uv.xyx+vec3(0,2,4));

    // Output to screen
    fragColor = vec4(col,1.0);
}
```

Pretty similar! And in fact, converting GLSL examples to Scala + Ultraviolet tends to be quite a straightforward and mechanical process. Much work has been done to make the syntax feel the same or better.
