package Aequivaleo_Release.buildTypes

import Aequivaleo_OfficialPublications.buildTypes.Aequivaleo_OfficialPublications_CommonB
import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Aequivaleo_Release_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Release to CurseForge"

    params {
        param("env.Version.Patch", "${Aequivaleo_OfficialPublications_CommonB.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(Aequivaleo_OfficialPublications.buildTypes.Aequivaleo_OfficialPublications_CommonB) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
