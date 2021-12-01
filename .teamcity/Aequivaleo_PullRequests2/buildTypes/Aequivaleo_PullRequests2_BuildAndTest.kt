package Aequivaleo_PullRequests2.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Aequivaleo_PullRequests2_BuildAndTest : BuildType({
    templates(_Self.buildTypes.BuildWithTesting)
    name = "Build and Test"
    description = "Builds and Tests the pull request."

    artifactRules = """
        +:build\libs\*.jar => build\libs
        +:build\distributions\mods-*.zip => build\distributions
    """.trimIndent()

    params {
        param("env.Version.Patch", "${Aequivaleo_PullRequests2_CommonBuildCounter.depParamRefs.buildNumber}")
        param("env.Version.Suffix", "-PR")
    }

    dependencies {
        snapshot(Aequivaleo_PullRequests2_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
    
    disableSettings("BUILD_EXT_15")
})
