package Core_Branches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Core_Branches_Build : BuildType({
    templates(_Self.buildTypes.Build)
    name = "Build"
    description = "Builds the branch without testing."
    paused = true

    params {
        param("Project.Type", "mods")
        param("env.Version.Patch", "${Core_Branches_Common.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(Core_Branches_Common) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
