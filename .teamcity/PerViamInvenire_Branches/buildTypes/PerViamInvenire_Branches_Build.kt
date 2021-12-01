package PerViamInvenire_Branches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object PerViamInvenire_Branches_Build : BuildType({
    templates(_Self.buildTypes.Build)
    name = "Build"
    description = "Builds the branch without testing."

    params {
        param("env.Version.Patch", "${PerViamInvenire_Branches_Common.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(PerViamInvenire_Branches_Common) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
