package DataGenerators_Alpha.buildTypes

import DataGenerators_OfficialPublications.buildTypes.DataGenerators_OfficialPublications_CommonB
import jetbrains.buildServer.configs.kotlin.v2019_2.*

object DataGenerators_Alpha_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha"

    params {
        param("env.Version.Patch", "${DataGenerators_OfficialPublications_CommonB.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(DataGenerators_OfficialPublications.buildTypes.DataGenerators_OfficialPublications_CommonB) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
