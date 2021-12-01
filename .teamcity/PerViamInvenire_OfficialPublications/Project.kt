package PerViamInvenire_OfficialPublications

import PerViamInvenire_OfficialPublications.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("PerViamInvenire_OfficialPublications")
    name = "Official Publications"
    description = "Holds projects and builds related to official publications"

    buildType(PerViamInvenire_OfficialPublications_CommonB)
})
