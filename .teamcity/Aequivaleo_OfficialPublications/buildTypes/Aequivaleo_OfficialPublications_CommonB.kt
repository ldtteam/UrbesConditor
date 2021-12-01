package Aequivaleo_OfficialPublications.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Aequivaleo_OfficialPublications_CommonB : BuildType({
    templates(_Self.buildTypes.CommonBuildCounter)
    name = "Common Build Counter"
    description = "Represents the version counter within Minecolonies for official releases."
})
