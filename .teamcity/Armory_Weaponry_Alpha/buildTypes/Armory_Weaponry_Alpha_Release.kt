package Armory_Weaponry_Alpha.buildTypes

import Armory_Weaponry_OfficialPublications.buildTypes.Armory_Weaponry_OfficialPublications_CommonBuildCounter
import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Armory_Weaponry_Alpha_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"
    paused = true

    params {
        param("Project.Type", "mods")
        param("env.Version.Patch", "${Armory_Weaponry_OfficialPublications_CommonBuildCounter.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(Armory_Weaponry_OfficialPublications.buildTypes.Armory_Weaponry_OfficialPublications_CommonBuildCounter) {
            runOnSameAgent = true
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
