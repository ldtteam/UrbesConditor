package _Self.buildTypes

import _Self.vcsRoots.General
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.vcsLabeling
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object BuildWithRelease : Template({
    name = "Build with Release"
    description = "Builds the current project and releases it to CurseForge"

    artifactRules = """+:build\libs\*.jar => build\libs"""
    buildNumberPattern = "%env.Version%"

    params {
        text("Repository", "", label = "Repostior", description = "The repository that this build targets.", allowEmpty = true)
        text("VCS.Branches", "+:refs/heads/(*)", label = "Branches", description = "Defines the branches that this build could target", allowEmpty = true)
        text("env.Version.Minor", "1", label = "Minor Version", description = "The minor version of the project",
              regex = "[0-9]+", validationMessage = "Please specify a number!")
        param("Project.Type", "")
        param("env.Version.Patch", "%build.number%%")
        param("Upsource.Project.Id", "")
        param("env.CURSE_RELEASE_TYPE", "%env.CURSERELEASETYPE%")
        text("env.Version.Major", "1", label = "Major Version", description = "The major version of the project.",
              regex = "[0-9]+", validationMessage = "Please specificy a number!")
        text("Default.Branch", "CI/Default", label = "Default branch", description = "The default branch of this build.", allowEmpty = true)
        param("jdk.version", "jdk16")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(_Self.vcsRoots.General)

        branchFilter = """
            +:*
            -:<default>
        """.trimIndent()
        showDependenciesChanges = true
    }

    steps {
        gradle {
            name = "Compile"
            id = "RUNNER_9"
            tasks = "build createChangelog curseforge publish"
            buildFile = "build.gradle"
            enableStacktrace = true
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerRunParameters = "-u 0 -v /opt/buildagent/gradle:/home/gradle/.gradle"
        }
    }

    triggers {
        vcs {
            id = "vcsTrigger"
        }
    }

    features {
        commitStatusPublisher {
            id = "BUILD_EXT_15"
            enabled = false
            vcsRootExtId = "${General.id}"
            publisher = upsource {
                serverUrl = "https://code-analysis.ldtteam.com"
                projectId = "%Upsource.Project.Id%"
                userName = "upsource"
                password = "credentialsJSON:f19631a7-1bc1-4a66-88a0-dc2b9cd36734"
            }
        }
        commitStatusPublisher {
            id = "BUILD_EXT_16"
            vcsRootExtId = "${General.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:0ab5d46b-ca88-4fa8-93d2-163fe135b15a"
                }
            }
        }
        vcsLabeling {
            id = "BUILD_EXT_11"
            vcsRootId = "${General.id}"
            labelingPattern = "%env.Version%"
            successfulOnly = true
            branchFilter = ""
        }
    }
})
