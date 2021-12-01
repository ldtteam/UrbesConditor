package SmithsCore_PullRequestsArchived.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object SmithsCore_PullRequestsArchived_TestMain : BuildType({
    templates(_Self.buildTypes.Test)
    name = "Test - Main"
    description = "Runs the tests for the main part of Minecolonies"
    paused = true

    params {
        param("Default.Package", "com.minecolonies.coremod")
    }

    dependencies {
        dependency(SmithsCore_PullRequestsArchived_CompileMain) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_1"
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
    }
})
