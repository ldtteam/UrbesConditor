package GraphicsExpanded_Alpha

import GraphicsExpanded_Alpha.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("GraphicsExpanded_Alpha")
    name = "Alpha"
    description = "Alpha version builds of minecolonies"

    buildType(GraphicsExpanded_Alpha_Release)

    params {
        param("Default.Branch", "version/%Current Minecraft Version%")
        param("VCS.Branches", "+:refs/heads/version/(*)")
        param("env.CURSERELEASETYPE", "alpha")
        param("env.Version.Suffix", "-ALPHA")
    }
})
