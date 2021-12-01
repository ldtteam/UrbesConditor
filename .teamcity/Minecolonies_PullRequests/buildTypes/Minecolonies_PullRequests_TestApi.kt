package Minecolonies_PullRequests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Minecolonies_PullRequests_TestApi : BuildType({
    templates(_Self.buildTypes.Test)
    name = "Test - API"
    description = "Runs the tests for the api of Minecolonies"
    paused = true

    params {
        param("Default.Package", "com.minecolonies.api")
    }

    dependencies {
        dependency(Minecolonies_PullRequests_CompileApi) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_2"
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
    }
})
