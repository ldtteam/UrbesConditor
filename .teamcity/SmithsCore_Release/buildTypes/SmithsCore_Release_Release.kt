package SmithsCore_Release.buildTypes

import SmithsCore_OfficialPublications.buildTypes.SmithsCore_OfficialPublications_CommonBuildCounter
import jetbrains.buildServer.configs.kotlin.v2019_2.*

object SmithsCore_Release_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"
    paused = true

    params {
        param("env.Version.Patch", "${SmithsCore_OfficialPublications_CommonBuildCounter.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(SmithsCore_OfficialPublications.buildTypes.SmithsCore_OfficialPublications_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
