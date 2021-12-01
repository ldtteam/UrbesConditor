package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

object DeployCurseForge : Template({
    name = "Deploy - CurseForge"

    enablePersonalBuilds = false
    artifactRules = """+:build\libs\*.jar"""
    type = BuildTypeSettings.Type.DEPLOYMENT
    buildNumberPattern = "%env.Version%"
    maxRunningBuilds = 1

    params {
        select("Artifacts.Repo.Type", "mods", label = "Artifact type", description = "The produced artifact types.",
                options = listOf("mods", "libraries"))
        text("Repository", "ldtteam/minecolonies", label = "Repostior", description = "The repository that this build targets.", allowEmpty = true)
        param("VCS.Branches", "+:refs/heads/(*)")
        text("env.Version.Minor", "1", label = "Minor Version", description = "The minor version of the project",
              regex = "[0-9]+", validationMessage = "Please specify a number!")
        text("env.Version.Major", "1", label = "Major Version", description = "The major version of the project.",
              regex = "[0-9]+", validationMessage = "Please specificy a number!")
        text("Default.Branch", "CI/Default", label = "Default branch", description = "The default branch of this build.", allowEmpty = true)
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%build.number%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(_Self.vcsRoots.General)

        showDependenciesChanges = true
    }

    steps {
        gradle {
            name = "Deploy"
            id = "RUNNER_77"
            tasks = "curseforge"
            buildFile = "build.gradle"
            useGradleWrapper = false
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerImage = "gradle:%gradle.version%-jdk%jdk.version%"
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseText", "%Artifacts.Repo.Type%")
            param("org.jfrog.artifactory.selectedDeployableServer.useM2CompatiblePatterns", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionNumberOfBuilds", "200")
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetention", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.publishMavenDescriptors", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.activateGradleIntegration", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseFlag", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionAsync", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.targetRepo", "libraries")
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.urlId", "2")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*,*key*,*KEY*")
            param("org.jfrog.artifactory.selectedDeployableServer.resolvingRepo", "modding")
            param("org.jfrog.artifactory.selectedDeployableServer.resolveReleaseText", "ldtteam")
            param("org.jfrog.artifactory.selectedDeployableServer.includePublishedArtifacts", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.includeEnvVars", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionDeleteArtifacts", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionMaxDays", "60")
            param("org.jfrog.artifactory.selectedDeployableServer.deployArtifacts", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.runLicenseChecks", "true")
        }
    }
})
