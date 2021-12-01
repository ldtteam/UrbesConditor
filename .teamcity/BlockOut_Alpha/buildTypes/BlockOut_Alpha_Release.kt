package BlockOut_Alpha.buildTypes

import BlockOut_OfficialPublications.buildTypes.BlockOut_OfficialPublications_CommonBuildCounter
import _Self.vcsRoots.General
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

object BlockOut_Alpha_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"

    artifactRules = """+:**\build\libs\*.jar => build\libs"""

    params {
        param("Project.Type", "mods")
        param("env.Version.Patch", "${BlockOut_OfficialPublications_CommonBuildCounter.depParamRefs.buildNumber}")
    }

    steps {
        gradle {
            name = "Setup"
            id = "RUNNER_83"
            tasks = "clean setupCIWorkspace --parallel"
            buildFile = "build.gradle"
            gradleParams = "-Pgradle.cache.push=true"
            useGradleWrapper = false
            param("org.jfrog.artifactory.selectedDeployableServer.useM2CompatiblePatterns", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.resolveReleaseFlag", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("org.jfrog.artifactory.selectedDeployableServer.buildDependencies", "Requires Artifactory Pro.")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*")
            param("org.jfrog.artifactory.selectedDeployableServer.resolvingRepo", "modding")
            param("org.jfrog.artifactory.selectedDeployableServer.resolveReleaseText", "%Project.Type%")
            param("org.jfrog.artifactory.selectedDeployableServer.publishMavenDescriptors", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.publishIvyDescriptors", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.targetRepo", "build-cache-gradle")
        }
        gradle {
            name = "Compile"
            id = "RUNNER_9"
            tasks = "build createChangelog curseforge"
            buildFile = "build.gradle"
            useGradleWrapper = false
            enableStacktrace = true
            coverageEngine = idea {
                includeClasses = "com.ldtteam.blockout.*"
                excludeClasses = "**.generated.**"
            }
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseText", "%Project.Type%")
            param("org.jfrog.artifactory.selectedDeployableServer.useM2CompatiblePatterns", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionNumberOfBuilds", "300")
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetention", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.publishMavenDescriptors", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.activateGradleIntegration", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.deployReleaseFlag", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionAsync", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.targetRepo", "libraries")
            param("org.jfrog.artifactory.selectedDeployableServer.projectUsesArtifactoryGradlePlugin", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.urlId", "2")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*")
            param("org.jfrog.artifactory.selectedDeployableServer.resolvingRepo", "modding")
            param("org.jfrog.artifactory.selectedDeployableServer.includePublishedArtifacts", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionDeleteArtifacts", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.publishIvyDescriptors", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionMaxDays", "150")
            param("org.jfrog.artifactory.selectedDeployableServer.deployArtifacts", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.runLicenseChecks", "true")
        }
    }

    features {
        commitStatusPublisher {
            id = "BUILD_EXT_15"
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
        snapshot(BlockOut_OfficialPublications.buildTypes.BlockOut_OfficialPublications_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
