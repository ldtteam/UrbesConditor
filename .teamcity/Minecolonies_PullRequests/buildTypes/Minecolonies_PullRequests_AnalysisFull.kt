package Minecolonies_PullRequests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Minecolonies_PullRequests_AnalysisFull : BuildType({
    templates(_Self.buildTypes.Analysis)
    name = "Analysis - Full"
    description = "Temporary analysis run on entire project."
    paused = true

    params {
        param("Analysis.Id", "Full")
        param("Sources", "")
        param("VCS.Branches", """
            -:refs/heads/*
            +:refs/pull/(*)/head
            -:refs/heads/(CI/*)
        """.trimIndent())
    }

    dependencies {
        dependency(Minecolonies_PullRequests_TestApi) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_14"
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
        dependency(Minecolonies_PullRequests_TestBlockOut) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_21"
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
        dependency(Minecolonies_PullRequests_TestMain) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_22"
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
        dependency(Minecolonies_PullRequests_TestStructures) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_23"
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
    }
})
