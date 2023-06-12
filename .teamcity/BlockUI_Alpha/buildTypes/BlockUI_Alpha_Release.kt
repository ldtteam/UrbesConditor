package BlockUI_Alpha.buildTypes

import BlockUI_OfficialPublications.buildTypes.BlockUI_OfficialPublications_CommonB
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object BlockUI_Alpha_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"

    allowExternalStatus = true

    params {
        param("gradle.version", "7.3")
        param("Default.Branch", "main")
        param("jdk.version", "jdk17")
        param("env.Version.Patch", "${BlockUI_OfficialPublications_CommonB.depParamRefs.buildNumber}")
    }

    triggers {
        vcs {
            id = "vcsTrigger"
        }
    }

    dependencies {
        snapshot(BlockUI_OfficialPublications.buildTypes.BlockUI_OfficialPublications_CommonB) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
