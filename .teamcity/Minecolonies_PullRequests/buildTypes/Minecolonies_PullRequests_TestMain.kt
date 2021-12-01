package Minecolonies_PullRequests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Minecolonies_PullRequests_TestMain : BuildType({
    templates(_Self.buildTypes.Test)
    name = "Test - Main"
    description = "Runs the tests for the main part of Minecolonies"
    paused = true

    params {
        param("Default.Package", "com.minecolonies.coremod")
    }

    dependencies {
        dependency(Minecolonies_PullRequests_CompileMain) {
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
