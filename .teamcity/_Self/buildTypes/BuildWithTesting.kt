package _Self.buildTypes

import _Self.vcsRoots.General
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object BuildWithTesting : Template({
    name = "Build with Testing"
    description = "Builds the current project without testing."

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
        text("env.Version.Major", "1", label = "Major Version", description = "The major version of the project.",
              regex = "[0-9]+", validationMessage = "Please specificy a number!")
        text("Default.Branch", "CI/Default", label = "Default branch", description = "The default branch of this build.", allowEmpty = true)
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(_Self.vcsRoots.General)

        showDependenciesChanges = true
    }

    steps {
        script {
            name = "Clean build dir"
            id = "RUNNER_85"
            enabled = false
            scriptContent = "rm -rf build"
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
        gradle {
            name = "Setup"
            id = "RUNNER_83"
            enabled = false
            tasks = "setupCIWorkspace --parallel"
            buildFile = "build.gradle"
            useGradleWrapper = false
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerImage = "gradle:%gradle.version%-jdk%jdk.version%"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("secure:org.jfrog.artifactory.selectedDeployableServer.deployerPassword", "credentialsJSON:c3b6b26d-a9f5-4697-aeec-6961bb8b04d2")
            param("org.jfrog.artifactory.selectedDeployableServer.deployerUsername", "Minecolonies_TeamCity")
        }
        script {
            name = "Determine gradle version"
            id = "RUNNER_136"
            workingDir = "gradle/wrapper"
            scriptContent = """
                VERSION=${'$'}(cat gradle-wrapper.properties | grep "distributionUrl" | cut -d '-' -f 2)
                echo "##teamcity[setParameter name='env.GRADLE_VERSION' value='${'$'}VERSION']"
                
                FILE="java-runtime.properties"
                VAR=""
                if test -f "${'$'}FILE"; then
                  VAR=${'$'}(cat java-runtime.properties | grep "version" | cut -d '=' -f 2)
                else
                  VAR="${'$'}{JDK_VERSION}"
                fi
                
                echo "##teamcity[setParameter name='env.JDK_VERSION' value='${'$'}VAR']"
            """.trimIndent()
            formatStderrAsError = true
            dockerImagePlatform = ScriptBuildStep.ImagePlatform.Linux
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
        gradle {
            name = "Compile"
            id = "RUNNER_9"
            tasks = "build"
            buildFile = "build.gradle"
            useGradleWrapper = false
            enableStacktrace = true
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerImage = "gradle:%env.GRADLE_VERSION%-%env.JDK_VERSION%"
            dockerRunParameters = """
                -v /opt/buildagent/gradle/caches:/home/gradle/.gradle/caches
                -u 0
            """.trimIndent()
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseText", "%Project.Type%")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionNumberOfBuilds", "300")
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetention", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseFlag", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionAsync", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.targetRepo", "libraries")
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.urlId", "2")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*")
            param("org.jfrog.artifactory.selectedDeployableServer.resolvingRepo", "modding")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionDeleteArtifacts", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionMaxDays", "150")
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
            vcsRootExtId = "${General.id}"
            publisher = upsource {
                serverUrl = "https://upsource.minecolonies.com"
                projectId = "%Upsource.Project.Id%"
                userName = "teamcity"
                password = "credentialsJSON:f19631a7-1bc1-4a66-88a0-dc2b9cd36734"
            }
        }
        commitStatusPublisher {
            id = "BUILD_EXT_16"
            vcsRootExtId = "${General.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:23cd3823-3e9f-4c80-a1c4-6ad9c876842a"
                }
            }
            param("github_oauth_user", "OrionDevelopment")
        }
    }
})
