package SmithsCore_PullRequests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object SmithsCore_PullRequests_BuildAndTest : BuildType({
    templates(_Self.buildTypes.BuildWithTesting)
    name = "Build and Test"
    description = "Builds and Tests the pull request."
    paused = true

    params {
        param("env.Version.Patch", "${SmithsCore_PullRequests_CommonBuildCounter.depParamRefs.buildNumber}")
        param("env.Version.Suffix", "-PR")
    }

    dependencies {
        snapshot(SmithsCore_PullRequests_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
