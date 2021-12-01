package GraphicsExpanded_Branches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object GraphicsExpanded_Branches_Build : BuildType({
    templates(_Self.buildTypes.Build)
    name = "Build"
    description = "Builds the branch without testing."

    params {
        param("env.Version.Patch", "${GraphicsExpanded_Branches_CommonBuildCounter.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(GraphicsExpanded_Branches_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
