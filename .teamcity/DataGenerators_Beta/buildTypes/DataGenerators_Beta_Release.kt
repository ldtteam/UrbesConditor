package DataGenerators_Beta.buildTypes

import DataGenerators_OfficialPublications.buildTypes.DataGenerators_OfficialPublications_CommonB
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

object DataGenerators_Beta_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Beta"

    params {
        param("env.Version.Patch", "${DataGenerators_OfficialPublications_CommonB.depParamRefs.buildNumber}")
    }

    steps {
        gradle {
            name = "Compile"
            id = "RUNNER_9"
            tasks = "build createChangelog curseforge"
            buildFile = "build.gradle"
            useGradleWrapper = false
            enableStacktrace = true
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
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionDeleteArtifacts", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.publishIvyDescriptors", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildRetentionMaxDays", "150")
            param("org.jfrog.artifactory.selectedDeployableServer.deployArtifacts", "true")
        }
    }

    dependencies {
        snapshot(DataGenerators_OfficialPublications.buildTypes.DataGenerators_OfficialPublications_CommonB) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
