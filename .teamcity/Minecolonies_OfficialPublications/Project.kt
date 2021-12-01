package Minecolonies_OfficialPublications

import Minecolonies_OfficialPublications.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Minecolonies_OfficialPublications")
    name = "Official Publications"
    description = "Holds projects and builds related to official publications"

    buildType(Minecolonies_OfficialPublications_CommonB)
})
