package PistonUnlimited_Alpha.buildTypes

import PistonUnlimited_OfficialPublications.buildTypes.PistonUnlimited_OfficialPublications_CommonB
import jetbrains.buildServer.configs.kotlin.v2019_2.*

object PistonUnlimited_Alpha_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"

    params {
        param("Project.Type", "mods")
        param("env.Version.Patch", "${PistonUnlimited_OfficialPublications_CommonB.depParamRefs.buildNumber}")
        param("Current Minecraft Version", "main")
    }

    dependencies {
        snapshot(PistonUnlimited_OfficialPublications.buildTypes.PistonUnlimited_OfficialPublications_CommonB) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
