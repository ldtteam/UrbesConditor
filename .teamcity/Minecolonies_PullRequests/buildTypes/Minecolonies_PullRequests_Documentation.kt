package Minecolonies_PullRequests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Minecolonies_PullRequests_Documentation : BuildType({
    templates(_Self.buildTypes.Documentation)
    name = "Documentation"
    description = "Generates the documentation."
    paused = true

    dependencies {
        dependency(Minecolonies_PullRequests_AnalysisPublish) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_11"
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
    }
})
