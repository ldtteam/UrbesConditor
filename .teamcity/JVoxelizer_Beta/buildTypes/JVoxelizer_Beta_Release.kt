package JVoxelizer_Beta.buildTypes

import JVoxelizer_OfficialPublications.buildTypes.JVoxelizer_OfficialPublications_CommonBuildCounter
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

object JVoxelizer_Beta_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Builds a beta release of JVoxelizer and publishes it to the maven repo."
    paused = true

    artifactRules = """+:**\build\libs\*.jar => build\libs"""

    params {
        param("env.Version.Patch", "${JVoxelizer_OfficialPublications_CommonBuildCounter.depParamRefs.buildNumber}")
    }

    steps {
        gradle {
            name = "Setup"
            id = "RUNNER_83"
            tasks = "clean setupCiWorkspace --parallel"
            buildFile = "build.gradle"
            useGradleWrapper = false
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("secure:org.jfrog.artifactory.selectedDeployableServer.deployerPassword", "credentialsJSON:c3b6b26d-a9f5-4697-aeec-6961bb8b04d2")
            param("org.jfrog.artifactory.selectedDeployableServer.deployerUsername", "Minecolonies_TeamCity")
        }
        gradle {
            name = "Compile"
            id = "RUNNER_9"
            tasks = "build --parallel"
            buildFile = "build.gradle"
            useGradleWrapper = false
            enableStacktrace = true
            coverageEngine = idea {
                includeClasses = "com.ldtteam.jvoxelizer"
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

    dependencies {
        snapshot(JVoxelizer_OfficialPublications.buildTypes.JVoxelizer_OfficialPublications_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
