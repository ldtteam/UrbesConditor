package PerViamInvenire_PullRequests2.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object PerViamInvenire_PullRequests2_BuildAndTest : BuildType({
    templates(_Self.buildTypes.BuildWithTesting)
    name = "Build and Test"
    description = "Builds and Tests the pull request."

    params {
        param("env.Version.Patch", "${PerViamInvenire_PullRequests2_CommonBuildCounter.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(PerViamInvenire_PullRequests2_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
