package SmithsCore_PullRequestsArchived.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object SmithsCore_PullRequestsArchived_AnalysisFull : BuildType({
    templates(_Self.buildTypes.Analysis)
    name = "Analysis - Full"
    description = "Temporary analysis run on entire project."
    paused = true

    params {
        param("Analysis.Id", "Full")
        param("Sources", "")
    }

    dependencies {
        dependency(SmithsCore_PullRequestsArchived_TestApi) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_14"
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
        dependency(SmithsCore_PullRequestsArchived_TestBlockOut) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_21"
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
        dependency(SmithsCore_PullRequestsArchived_TestMain) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_22"
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
        dependency(SmithsCore_PullRequestsArchived_TestStructures) {
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
