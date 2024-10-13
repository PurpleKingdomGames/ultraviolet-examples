# Ultraviolet Examples

This repository is meant to act as 'living documentation' for Ultraviolet, a Scala 3 to GLSL transpiler. Small, simple, reference examples of how to use Ultraviolet, and perhaps a few larger demos too.

No example is too small, no library feature unworthy of a demo. If you think something is missing, please feel free to contribute.

To browse the running examples, go to [https://purplekingdomgames.github.io/ultraviolet-examples/](https://purplekingdomgames.github.io/ultraviolet-examples/).

All the shaders are written in [Ultraviolet](https://github.com/PurpleKingdomGames/ultraviolet) and presented running in [Indigo](https://indigoengine.io/), but Ultraviolet will work in any context where you need to generate GLSL from Scala.

## Further reading on Shaders

Nothing I've done here or anywhere else with regard to shaders is original. Here are a few links to shader related goodness, by people far cleverer than myself.

- [https://thebookofshaders.com/](https://thebookofshaders.com/)
- [https://iquilezles.org/articles/](https://iquilezles.org/articles/)
- [https://github.com/ashima/webgl-noise](https://github.com/ashima/webgl-noise)
- [https://www.shadertoy.com/](https://www.shadertoy.com/)

## Building the examples

### Setting up Mill using Millw

[Millw](https://github.com/lefou/millw) is already in the repo (linux / MacOS, Windows is missing), and should 'just work' as a drop in replacement for the normal Mill executable, but just in case, you can install it like this:

```sh
curl -L https://raw.githubusercontent.com/lefou/millw/0.4.7/millw > mill && chmod +x mill
```

### Running the shaders locally

The shaders are arranged in a tree based on their folders, with the 'shaders' folder omitted. So to run the campfire shader, you would look at the directory structure, and do:

```sh
./mill shaders.demos.campfire.runGame
```

Note that single quotes allow you to run projects with hyphenated names in zsh, e.g.:

```sh
./mill 'shaders.noise.white-noise.runGame'
```

If you'd like to compile and test everything, you can do the following:

```sh
./mill shaders.__.compile
./mill shaders.__.test
```

### Regenerating the website

This command re-generates the website into the docs folder. It will take quite a while...

```sh
./mill shaders.genSite
```

If you then `cd docs/`, you can run the following to run the site locally.


```sh
npm install http-server
npx http-server -c-1 
```
