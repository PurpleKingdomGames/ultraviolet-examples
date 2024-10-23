import $ivy.`com.lihaoyi::mill-contrib-bloop:$MILL_VERSION`
import mill._
import mill.scalalib._
import mill.scalajslib._
import mill.scalajslib.api._

import $file.scripts.shadermodule

import indigoplugin._

object examples extends mill.Module {

  object fragment extends mill.Module {

    object basics extends mill.Module {

      object colours extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Colours")
      }
      object minimal extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Minimal")
      }

    }

    object demos extends mill.Module {

      object campfire extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Campfire")
            .withWindowSize(192, 192)
      }

      object pulsingbox extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("PulsingBox")
      }

      object `glowing-star` extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Glowing Star")
      }

    }

    object noise extends mill.Module {

      object `cellular-noise` extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Cellular Noise")
      }

      object `classic-perlin-noise` extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Classic Perlin Noise")
      }

      object `gradient-noise` extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Gradient Noise")
      }

      object `simplex-noise` extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Simplex Noise")
      }

      object `white-noise` extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("White Noise")
      }

    }

    object patterns extends mill.Module {

      object grid extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Grid")
      }

      object rainbow extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Rainbow")
      }

      object `simple-voronoi` extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Simple Voronoi")
      }

      object stripes extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Stripes")
      }

      object `wavy-stripes` extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("WavyStripes")
      }

    }

    object sdf extends mill.Module {

      object circle extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Circle SDF")
      }

      object square extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Square SDF")
      }

      object star extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Star")
      }

    }

    object shapes extends mill.Module {

      object doughnut extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Doughnut")
      }

      object metaballs extends shadermodule.ShaderModule {
        val indigoOptions: IndigoOptions =
          makeIndigoOptions("Metaballs")
      }

    }

  }

  object `language-reference` extends mill.Module {

    object imports extends shadermodule.ShaderModule {
      val indigoOptions: IndigoOptions =
        makeIndigoOptions("Imports")
    }

  }

  object vertex extends mill.Module {

    object minimal extends shadermodule.ShaderModule {
      val indigoOptions: IndigoOptions =
        makeIndigoOptions("Minimal")
    }

  }

}
