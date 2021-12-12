package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'Structurize_Release_Release'
in the project with id = 'Structurize_Release', and delete the patch script.
*/
create(RelativeId("Structurize_Release"), BuildType({
    templates(RelativeId("BuildWithRelease"))
    id("Structurize_Release_Release")
    name = "Release"
    description = "Releases the mod as Alpha to CurseForge"

    params {
        param("Project.Type", "mods")
        param("env.Version.Patch", "%dep.LetSDevTogether_Structurize_OfficialPublications_CommonB.build.number%")
    }

    dependencies {
        snapshot(RelativeId("Structurize_OfficialPublications_CommonB")) {
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
}))

