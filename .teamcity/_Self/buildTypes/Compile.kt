package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object Compile : Template({
    name = "Compile"

    artifactRules = """+:build\libs\*.jar => build\libs"""
    buildNumberPattern = "%env.Version%"

    params {
        text("Repository", "Minecolonies/minecolonies", label = "Repostior", description = "The repository that this build targets.", allowEmpty = true)
        text("VCS.Branches", "+:refs/heads/(*)", label = "Branches", description = "Defines the branches that this build could target", allowEmpty = true)
        text("Compile.JarType", "", label = "Compile type", description = "The compile type. EG: api, deobf, etc. Leave empty to compile main jar.", allowEmpty = true)
        text("env.Version.Minor", "1", label = "Minor Version", description = "The minor version of the project",
              regex = "[0-9]+", validationMessage = "Please specify a number!")
        param("env.Version.Patch", "%build.number%%")
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
            scriptContent = "rm -rf build"
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
        gradle {
            name = "Setup"
            id = "RUNNER_83"
            tasks = "setupCIWorkspace --parallel"
            buildFile = "build.gradle"
            useGradleWrapper = false
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerImage = "gradle:%gradle.version%-jdk%jdk.version%"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("secure:org.jfrog.artifactory.selectedDeployableServer.deployerPassword", "credentialsJSON:c3b6b26d-a9f5-4697-aeec-6961bb8b04d2")
            param("org.jfrog.artifactory.selectedDeployableServer.deployerUsername", "Minecolonies_TeamCity")
        }
        gradle {
            name = "Compile"
            id = "RUNNER_9"
            tasks = "reobf%Compile.JarType%Jar --parallel"
            buildFile = "build.gradle"
            gradleParams = "-x test -x javadoc"
            useGradleWrapper = false
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerImage = "gradle:%gradle.version%-jdk%jdk.version%"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("secure:org.jfrog.artifactory.selectedDeployableServer.deployerPassword", "credentialsJSON:c3b6b26d-a9f5-4697-aeec-6961bb8b04d2")
            param("org.jfrog.artifactory.selectedDeployableServer.deployerUsername", "Minecolonies_TeamCity")
        }
        gradle {
            name = "Sources"
            id = "RUNNER_87"
            tasks = "sourceJar --parallel"
            buildFile = "build.gradle"
            useGradleWrapper = false
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerImage = "gradle:%gradle.version%-jdk%jdk.version%"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("secure:org.jfrog.artifactory.selectedDeployableServer.deployerPassword", "credentialsJSON:c3b6b26d-a9f5-4697-aeec-6961bb8b04d2")
            param("org.jfrog.artifactory.selectedDeployableServer.deployerUsername", "Minecolonies_TeamCity")
        }
    }
})
