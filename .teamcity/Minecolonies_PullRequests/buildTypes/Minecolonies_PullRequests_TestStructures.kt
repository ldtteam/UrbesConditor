package Minecolonies_PullRequests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Minecolonies_PullRequests_TestStructures : BuildType({
    templates(_Self.buildTypes.Test)
    name = "Test - Structures"
    description = "Runs the tests for the structures part of Minecolonies"
    paused = true

    params {
        param("Default.Package", "com.minecolonies.structures")
    }

    dependencies {
        dependency(Minecolonies_PullRequests_CompileStructures) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_4"
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
    }
})
