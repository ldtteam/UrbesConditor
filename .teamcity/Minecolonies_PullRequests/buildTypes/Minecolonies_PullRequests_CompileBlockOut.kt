package Minecolonies_PullRequests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Minecolonies_PullRequests_CompileBlockOut : BuildType({
    templates(_Self.buildTypes.Compile)
    name = "Compile - BlockOut"
    description = "Compiles the blockout jar of the Minecolonies Project"
    paused = true

    params {
        param("Compile.JarType", "blockOut")
    }
})
