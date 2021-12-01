package JVoxelizer_PullRequests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

object JVoxelizer_PullRequests_BuildAndTest : BuildType({
    templates(_Self.buildTypes.BuildWithTesting)
    name = "Build and Test"
    description = "Builds and Tests the pull request."
    paused = true

    artifactRules = """+:**\build\libs\*.jar => build\libs"""

    params {
        param("env.Version.Patch", "${JVoxelizer_PullRequests_CommonBuildCounter.depParamRefs.buildNumber}")
        param("env.Version.Suffix", "-PR")
    }

    steps {
        gradle {
            name = "Setup"
            id = "RUNNER_83"
            tasks = "clean setupCiWorkspace --parallel"
            buildFile = "build.gradle"
            useGradleWrapper = false
            dockerImagePlatform = GradleBuildStep.ImagePlatform.Linux
            dockerImage = "gradle:%gradle.version%-jdk%jdk.version%"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("secure:org.jfrog.artifactory.selectedDeployableServer.deployerPassword", "credentialsJSON:c3b6b26d-a9f5-4697-aeec-6961bb8b04d2")
            param("org.jfrog.artifactory.selectedDeployableServer.deployerUsername", "Minecolonies_TeamCity")
        }
    }

    dependencies {
        snapshot(JVoxelizer_PullRequests_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
