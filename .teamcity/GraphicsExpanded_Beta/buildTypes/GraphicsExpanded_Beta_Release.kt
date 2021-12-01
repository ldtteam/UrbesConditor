package GraphicsExpanded_Beta.buildTypes

import GraphicsExpanded_OfficialPublications.buildTypes.GraphicsExpanded_OfficialPublications_CommonBuildCounter
import jetbrains.buildServer.configs.kotlin.v2019_2.*

object GraphicsExpanded_Beta_Release : BuildType({
    templates(_Self.buildTypes.BuildWithRelease)
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"

    params {
        param("env.Version.Patch", "${GraphicsExpanded_OfficialPublications_CommonBuildCounter.depParamRefs.buildNumber}")
    }

    dependencies {
        snapshot(GraphicsExpanded_OfficialPublications.buildTypes.GraphicsExpanded_OfficialPublications_CommonBuildCounter) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
