package PerViamInvenire_Release.buildTypes

import PerViamInvenire_OfficialPublications.buildTypes.PerViamInvenire_OfficialPublications_CommonB
import jetbrains.buildServer.configs.kotlin.v2019_2.*

object PerViamInvenire_Release_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Release to CurseForge"

    params {
        param("env.Version.Patch", "${PerViamInvenire_OfficialPublications_CommonB.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(PerViamInvenire_OfficialPublications.buildTypes.PerViamInvenire_OfficialPublications_CommonB) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
