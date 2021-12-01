package Aequivaleo_Alpha.buildTypes

import Aequivaleo_OfficialPublications.buildTypes.Aequivaleo_OfficialPublications_CommonB
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

object Aequivaleo_Alpha_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"

    allowExternalStatus = true

    params {
        param("env.Version.Patch", "${Aequivaleo_OfficialPublications_CommonB.depParamRefs.buildNumber}")
        param("Default.Branch", "version/latest")
    }

    steps {
        gradle {
            name = "Analyze"
            id = "RUNNER_140"
            tasks = "sonarqube"
            gradleParams = "-Dsonar.projectKey=ldtteam_Aequivaleo -Dsonar.host.url=https://code-analysis.ldtteam.com -Dsonar.login=%sonarqube.token%"
            dockerImage = "gradle:%env.GRADLE_VERSION%-%env.JDK_VERSION%"
            dockerRunParameters = "-u root -v /opt/buildagent/gradle/caches:/home/gradle/.gradle/caches"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
        }
    }

    dependencies {
        snapshot(Aequivaleo_OfficialPublications.buildTypes.Aequivaleo_OfficialPublications_CommonB) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
    
    disableSettings("RUNNER_134")
})
