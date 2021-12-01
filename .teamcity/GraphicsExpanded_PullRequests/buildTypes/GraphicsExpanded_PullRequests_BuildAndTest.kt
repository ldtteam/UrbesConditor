package GraphicsExpanded_PullRequests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object GraphicsExpanded_PullRequests_BuildAndTest : BuildType({
    templates(_Self.buildTypes.BuildWithTesting)
    name = "Build and Test"
    description = "Builds and Tests the pull request."

    params {
        param("env.Version.Patch", "${GraphicsExpanded_PullRequests_CommonBuildCounter.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(GraphicsExpanded_PullRequests_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
