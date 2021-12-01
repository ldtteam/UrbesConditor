package PerViamInvenire_Alpha.buildTypes

import PerViamInvenire_OfficialPublications.buildTypes.PerViamInvenire_OfficialPublications_CommonB
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object PerViamInvenire_Alpha_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"

    allowExternalStatus = true

    params {
        param("env.Version.Patch", "${PerViamInvenire_OfficialPublications_CommonB.depParamRefs.buildNumber}")
    }

    steps {
        script {
            name = "Clean build dir"
            id = "RUNNER_85"
            scriptContent = """
                rm -rf build
                chmod -R 777 ./*
            """.trimIndent()
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
    }

    dependencies {
        snapshot(PerViamInvenire_OfficialPublications.buildTypes.PerViamInvenire_OfficialPublications_CommonB) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
