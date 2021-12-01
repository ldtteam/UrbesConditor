package BlockOut_PullRequests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object BlockOut_PullRequests_BuildAndTest : BuildType({
    templates(_Self.buildTypes.BuildWithTesting)
    name = "Build and Test"
    description = "Builds and Tests the pull request."

    artifactRules = """+:**\build\libs\*.jar => build\libs"""

    params {
        param("Project.Type", "mods")
        param("env.Version.Patch", "${BlockOut_PullRequests_CommonBuildCounter.depParamRefs.buildNumber}")
        param("env.Version.Suffix", "-PR")
    }

    dependencies {
        snapshot(BlockOut_PullRequests_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
