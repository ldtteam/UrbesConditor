package SmithsCore_PullRequestsArchived.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object SmithsCore_PullRequestsArchived_AnalysisPublish : BuildType({
    name = "Analysis - Publish"
    description = "Publishes the analysis results to github."
    paused = true

    type = BuildTypeSettings.Type.COMPOSITE
    buildNumberPattern = "%env.Version%"

    vcs {
        root(_Self.vcsRoots.General)

        showDependenciesChanges = true
    }

    features {
        feature {
            type = "com.ldtteam.teamcity.github.commenting.GithubCommentingBuildFeature"
            param("password", "Minecolonies1!")
            param("branch", "%teamcity.build.branch%")
            param("token", "0bac7f748697cc12cc2d7bc25d83fb56e52e6265")
            param("username", "LDTTeam-Buildsystem")
        }
    }

    dependencies {
        dependency(SmithsCore_PullRequestsArchived_AnalysisFull) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
    }
})
