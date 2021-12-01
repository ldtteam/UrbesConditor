package Armory_Weaponry_Branches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Armory_Weaponry_Branches_Build : BuildType({
    templates(_Self.buildTypes.Build)
    name = "Build"
    description = "Builds the branch without testing."
    paused = true

    params {
        param("Project.Type", "mods")
        param("env.Version.Patch", "${Armory_Weaponry_Branches_CommonBuildCounter.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(Armory_Weaponry_Branches_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
