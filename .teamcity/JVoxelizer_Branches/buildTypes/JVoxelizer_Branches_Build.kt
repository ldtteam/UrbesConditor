package JVoxelizer_Branches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

object JVoxelizer_Branches_Build : BuildType({
    templates(_Self.buildTypes.Build)
    name = "Build"
    description = "Builds the branch without testing."
    paused = true

    artifactRules = """+:**\build\libs\*.jar => build\libs"""

    params {
        param("env.Version.Patch", "${JVoxelizer_Branches_CommonBuildCounter.depParamRefs.buildNumber}")
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
        snapshot(JVoxelizer_Branches_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
