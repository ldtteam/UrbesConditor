package SmithsCore_PullRequestsArchived.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object SmithsCore_PullRequestsArchived_TestBlockOut : BuildType({
    templates(_Self.buildTypes.Test)
    name = "Test - BlockOut"
    description = "Runs the tests for the BlockOut part of Minecolonies"
    paused = true

    params {
        param("Default.Package", "com.minecolonies.blockout")
    }

    dependencies {
        dependency(SmithsCore_PullRequestsArchived_CompileBlockOut) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_3"
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
    }
})
