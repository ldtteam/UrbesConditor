package BlockOut_Release.buildTypes

import BlockOut_OfficialPublications.buildTypes.BlockOut_OfficialPublications_CommonBuildCounter
import jetbrains.buildServer.configs.kotlin.v2019_2.*

object BlockOut_Release_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"

    artifactRules = """+:**\build\libs\*.jar => build\libs"""

    params {
        param("env.Version.Patch", "${BlockOut_OfficialPublications_CommonBuildCounter.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(BlockOut_OfficialPublications.buildTypes.BlockOut_OfficialPublications_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
