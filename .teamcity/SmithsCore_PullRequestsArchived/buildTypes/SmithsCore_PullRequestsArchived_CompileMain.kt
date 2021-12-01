package SmithsCore_PullRequestsArchived.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object SmithsCore_PullRequestsArchived_CompileMain : BuildType({
    templates(_Self.buildTypes.Compile)
    name = "Compile - Main"
    description = "Compiles the main jar of the Minecolonies Project"
    paused = true
})
