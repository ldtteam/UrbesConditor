package Minecolonies_PullRequests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Minecolonies_PullRequests_CompileStructures : BuildType({
    templates(_Self.buildTypes.Compile)
    name = "Compile - Structures"
    description = "Compiles the structures jar of the Minecolonies Project"
    paused = true

    params {
        param("Compile.JarType", "structures")
    }
})
