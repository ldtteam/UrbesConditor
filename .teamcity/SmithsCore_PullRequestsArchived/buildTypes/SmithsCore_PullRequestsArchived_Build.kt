package SmithsCore_PullRequestsArchived.buildTypes

import _Self.vcsRoots.General
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object SmithsCore_PullRequestsArchived_Build : BuildType({
    name = "Build"
    description = "Runs the entire build process"
    paused = true

    allowExternalStatus = true
    type = BuildTypeSettings.Type.COMPOSITE
    buildNumberPattern = "%env.Version%"

    params {
        param("Repository", "Minecolonies/minecolonies")
        param("VCS.Branches", """
            -:refs/heads/*
            +:refs/pull/(*)/merge
            -:refs/heads/(CI/*)
        """.trimIndent())
    }

    vcs {
        root(_Self.vcsRoots.General)

        showDependenciesChanges = true
    }

    triggers {
        vcs {
        }
    }

    features {
        commitStatusPublisher {
            vcsRootExtId = "${General.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:6131cd14-fa05-44e0-bcb6-d834103f967c"
                }
            }
            param("github_oauth_user", "OrionDevelopment")
        }
    }

    dependencies {
        dependency(SmithsCore_PullRequestsArchived_Documentation) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                artifactRules = """+:build\libs\*.jar => build\libs"""
            }
        }
    }
})
