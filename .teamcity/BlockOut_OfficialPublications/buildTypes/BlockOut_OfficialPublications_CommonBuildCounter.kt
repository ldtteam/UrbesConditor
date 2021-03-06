package BlockOut_OfficialPublications.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object BlockOut_OfficialPublications_CommonBuildCounter : BuildType({
    templates(_Self.buildTypes.CommonBuildCounter)
    name = "Common Build Counter"
    description = "Represents the version counter within Minecolonies for official releases."
})
