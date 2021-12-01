package PistonUnlimited_OfficialPublications

import PistonUnlimited_OfficialPublications.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("PistonUnlimited_OfficialPublications")
    name = "Official Publications"
    description = "Holds projects and builds related to official publications"

    buildType(PistonUnlimited_OfficialPublications_CommonB)
})
