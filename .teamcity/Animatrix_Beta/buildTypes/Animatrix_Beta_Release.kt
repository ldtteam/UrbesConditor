package Animatrix_Beta.buildTypes

import Animatrix_OfficialPublications.buildTypes.Animatrix_OfficialPublications_CommonB
import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Animatrix_Beta_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"

    params {
        param("env.Version.Patch", "${Animatrix_OfficialPublications_CommonB.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(Animatrix_OfficialPublications.buildTypes.Animatrix_OfficialPublications_CommonB) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
