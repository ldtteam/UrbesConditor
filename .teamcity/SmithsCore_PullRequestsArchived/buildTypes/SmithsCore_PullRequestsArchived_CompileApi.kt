package SmithsCore_PullRequestsArchived.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object SmithsCore_PullRequestsArchived_CompileApi : BuildType({
    templates(_Self.buildTypes.Compile)
    name = "Compile - API"
    description = "Compiles the api jar of the Minecolonies Project"
    paused = true

    params {
        param("Compile.JarType", "api")
    }
})
