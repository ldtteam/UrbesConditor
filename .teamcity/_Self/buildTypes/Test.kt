package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.ExecBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.exec
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

object Test : Template({
    name = "Test"

    artifactRules = """+:build\libs\*.jar => build\libs"""
    buildNumberPattern = "%env.Version%"

    params {
        text("Repository", "Minecolonies/minecolonies", label = "Repostior", description = "The repository that this build targets.", allowEmpty = true)
        param("VCS.Branches", "+:refs/heads/(*)")
        text("env.Version.Minor", "1", label = "Minor Version", description = "The minor version of the project",
              regex = "[0-9]+", validationMessage = "Please specify a number!")
        text("env.Version.Major", "1", label = "Major Version", description = "The major version of the project.",
              regex = "[0-9]+", validationMessage = "Please specificy a number!")
        text("Default.Branch", "CI/Default", label = "Default branch", description = "The default branch of this build.", readOnly = true, allowEmpty = true)
        text("Default.Package", "com.minecolonies", label = "Primary package", description = "The package root of the project that is targeted by the build.",
              regex = """^[a-z][a-z0-9_]*(\.[a-z0-9_]+)+[0-9a-z_]${'$'}""", validationMessage = "Please enter a valid Java package name.")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%build.number%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(_Self.vcsRoots.General)

        showDependenciesChanges = true
    }

    steps {
        gradle {
            name = "Version output"
            id = "RUNNER_56"
            buildFile = "build.gradle"
            gradleParams = "-v"
            useGradleWrapper = false
            enableDebug = true
            enableStacktrace = true
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerImage = "gradle:%gradle.version%-jdk%jdk.version%"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
        }
        gradle {
            name = "Test"
            id = "RUNNER_9"
            tasks = "test --tests %Default.Package%.*"
            buildFile = "build.gradle"
            gradleParams = "-x javadoc"
            useGradleWrapper = false
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerImage = "gradle:%gradle.version%-jdk%jdk.version%"
            coverageEngine = idea {
                includeClasses = "%Default.Package%.*"
            }
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
        }
        exec {
            name = "Test - CL"
            id = "RUNNER_59"
            enabled = false
            path = "gradle"
            arguments = "test --tests %Default.Package%.* -x javadoc"
            dockerImagePlatform = ExecBuildStep.ImagePlatform.Linux
            dockerImage = "gradle:%gradle.version%-jdk%jdk.version%"
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
    }
})
