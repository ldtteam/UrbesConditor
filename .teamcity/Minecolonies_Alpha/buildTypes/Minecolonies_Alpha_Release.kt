package Minecolonies_Alpha.buildTypes

import Minecolonies_OfficialPublications.buildTypes.Minecolonies_OfficialPublications_CommonB
import _Self.vcsRoots.General
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.vcsLabeling
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

object Minecolonies_Alpha_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"

    allowExternalStatus = true

    params {
        param("env.Version.Patch", "${Minecolonies_OfficialPublications_CommonB.depParamRefs.buildNumber}")
    }

    steps {
        gradle {
            name = "Compile"
            id = "RUNNER_9"
            tasks = "build createChangelog curseforge publish"
            buildFile = "build.gradle"
            enableStacktrace = true
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerImage = "gradle:%env.GRADLE_VERSION%-%env.JDK_VERSION%"
            dockerRunParameters = """
                -v /opt/buildagent/gradle/caches:/home/gradle/.gradle/caches
                -u 0
            """.trimIndent()
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseText", "%Project.Type%")
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("org.jfrog.artifactory.selectedDeployableServer.urlId", "2")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*")
            param("org.jfrog.artifactory.selectedDeployableServer.resolvingRepo", "modding")
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseFlag", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.targetRepo", "libraries")
        }
        gradle {
            name = "Analyze"
            id = "RUNNER_144"
            tasks = "sonarqube"
            buildFile = "build.gradle"
            gradleParams = "-Dsonar.projectKey=ldtteam_minecolonies -Dsonar.host.url=https://code-analysis.ldtteam.com -Dsonar.login=%sonarqube.token%"
            enableStacktrace = true
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerImage = "gradle:%env.GRADLE_VERSION%-%env.JDK_VERSION%"
            dockerRunParameters = """
                -v /opt/buildagent/gradle/caches:/home/gradle/.gradle/caches
                -u 0
            """.trimIndent()
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseText", "%Project.Type%")
            param("org.jfrog.artifactory.selectedDeployableServer.useM2CompatiblePatterns", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("org.jfrog.artifactory.selectedDeployableServer.buildDependencies", "Requires Artifactory Pro.")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*")
            param("org.jfrog.artifactory.selectedDeployableServer.publishMavenDescriptors", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.publishIvyDescriptors", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseFlag", "true")
        }
        stepsOrder = arrayListOf("RUNNER_85", "RUNNER_9", "RUNNER_144")
    }

    features {
        vcsLabeling {
            id = "BUILD_EXT_11"
            vcsRootId = "${General.id}"
            labelingPattern = "%env.Version%"
            successfulOnly = true
            branchFilter = ""
        }
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
    }

    dependencies {
        snapshot(Minecolonies_OfficialPublications.buildTypes.Minecolonies_OfficialPublications_CommonB) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
