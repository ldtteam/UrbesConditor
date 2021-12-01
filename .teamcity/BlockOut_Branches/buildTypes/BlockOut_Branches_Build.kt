package BlockOut_Branches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object BlockOut_Branches_Build : BuildType({
    templates(_Self.buildTypes.Build)
    name = "Build"
    description = "Builds the branch without testing."

    artifactRules = """+:**\build\libs\*.jar => build\libs"""

    params {
        param("Project.Type", "mods")
        param("env.Version.Patch", "${BlockOut_Branches_CommonBuildCounter.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(BlockOut_Branches_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
