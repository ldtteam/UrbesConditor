package Minecolonies_Branches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object Minecolonies_Branches_Build : BuildType({
    templates(_Self.buildTypes.Build)
    name = "Build"
    description = "Builds the branch without testing."

    params {
        param("Project.Type", "mods")
        param("env.Version.Patch", "${Minecolonies_Branches_Common.depParamRefs.buildNumber}")
    }

    triggers {
        vcs {
            id = "vcsTrigger"
        }
    }

    dependencies {
        snapshot(Minecolonies_Branches_Common) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
    
    disableSettings("BUILD_EXT_14")
})
