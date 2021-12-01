package SmithsCore_PullRequestsArchived.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object SmithsCore_PullRequestsArchived_CompileStructures : BuildType({
    templates(_Self.buildTypes.Compile)
    name = "Compile - Structures"
    description = "Compiles the structures jar of the Minecolonies Project"
    paused = true

    params {
        param("Compile.JarType", "structures")
    }
})
