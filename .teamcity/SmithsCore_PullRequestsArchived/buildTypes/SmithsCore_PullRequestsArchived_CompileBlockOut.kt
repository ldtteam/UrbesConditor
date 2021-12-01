package SmithsCore_PullRequestsArchived.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object SmithsCore_PullRequestsArchived_CompileBlockOut : BuildType({
    templates(_Self.buildTypes.Compile)
    name = "Compile - BlockOut"
    description = "Compiles the blockout jar of the Minecolonies Project"
    paused = true

    params {
        param("Compile.JarType", "blockOut")
    }
})
