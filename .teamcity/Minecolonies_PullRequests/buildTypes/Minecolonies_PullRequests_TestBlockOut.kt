package Minecolonies_PullRequests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Minecolonies_PullRequests_TestBlockOut : BuildType({
    templates(_Self.buildTypes.Test)
    name = "Test - BlockOut"
    description = "Runs the tests for the BlockOut part of Minecolonies"
    paused = true

    params {
        param("Default.Package", "com.minecolonies.blockout")
    }

    dependencies {
        dependency(Minecolonies_PullRequests_CompileBlockOut) {
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
