package Armory_Weaponry_OfficialPublications

import Armory_Weaponry_OfficialPublications.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Armory_Weaponry_OfficialPublications")
    name = "Official Publications"
    description = "Holds projects and builds related to official publications"
    archived = true

    buildType(Armory_Weaponry_OfficialPublications_CommonBuildCounter)
})
