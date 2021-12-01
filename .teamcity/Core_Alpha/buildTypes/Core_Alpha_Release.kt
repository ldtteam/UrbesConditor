package Core_Alpha.buildTypes

import Minecolonies_OfficialPublications.buildTypes.Minecolonies_OfficialPublications_CommonB
import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Core_Alpha_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"
    paused = true

    params {
        param("Project.Type", "mods")
        param("env.Version.Patch", "${Minecolonies_OfficialPublications_CommonB.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(Minecolonies_OfficialPublications.buildTypes.Minecolonies_OfficialPublications_CommonB) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
