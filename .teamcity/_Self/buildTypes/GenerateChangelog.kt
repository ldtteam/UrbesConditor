package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

object GenerateChangelog : Template({
    name = "Generate Changelog"

    artifactRules = """+:build\libs\*.jar => build\libs"""
    buildNumberPattern = "%env.Version%"

    params {
        text("env.Version.Major", "1", label = "Major Version", description = "The major version of the project.",
              regex = "[0-9]+", validationMessage = "Please specificy a number!")
        text("Default.Branch", "CI/Default", label = "Default branch", description = "The default branch of this build.", allowEmpty = true)
        text("Repository", "Minecolonies/minecolonies", label = "Repostior", description = "The repository that this build targets.", allowEmpty = true)
        param("VCS.Branches", "+:refs/heads/(*)")
        text("env.Version.Minor", "1", label = "Minor Version", description = "The minor version of the project",
              regex = "[0-9]+", validationMessage = "Please specify a number!")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%build.number%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(_Self.vcsRoots.General)

        showDependenciesChanges = true
    }

    steps {
        gradle {
            name = "Generate"
            id = "RUNNER_77"
            tasks = "createChangelog"
            buildFile = "build.gradle"
            gradleParams = "-x build -x test -x javadoc"
            useGradleWrapper = false
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerImage = "gradle:%gradle.version%-jdk%jdk.version%"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
        }
    }
})
