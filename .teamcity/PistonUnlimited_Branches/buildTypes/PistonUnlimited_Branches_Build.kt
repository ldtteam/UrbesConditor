package PistonUnlimited_Branches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object PistonUnlimited_Branches_Build : BuildType({
    templates(_Self.buildTypes.Build)
    name = "Build"
    description = "Builds the branch without testing."

    params {
        param("Project.Type", "mods")
        param("env.Version.Patch", "${PistonUnlimited_Branches_Common.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(PistonUnlimited_Branches_Common) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
