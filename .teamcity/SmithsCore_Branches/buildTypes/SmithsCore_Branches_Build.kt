package SmithsCore_Branches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object SmithsCore_Branches_Build : BuildType({
    templates(_Self.buildTypes.Build)
    name = "Build"
    description = "Builds the branch without testing."
    paused = true

    params {
        param("env.Version.Patch", "${SmithsCore_Branches_CommonBuildCounter.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(SmithsCore_Branches_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
