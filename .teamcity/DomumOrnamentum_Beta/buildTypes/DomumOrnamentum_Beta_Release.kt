package DomumOrnamentum_Beta.buildTypes

import DomumOrnamentum_OfficialPublications.buildTypes.DomumOrnamentum_OfficialPublications_CommonB
import jetbrains.buildServer.configs.kotlin.v2019_2.*

object DomumOrnamentum_Beta_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"

    params {
        param("Project.Type", "mods")
        param("env.Version.Patch", "${DomumOrnamentum_OfficialPublications_CommonB.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(DomumOrnamentum_OfficialPublications.buildTypes.DomumOrnamentum_OfficialPublications_CommonB) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
