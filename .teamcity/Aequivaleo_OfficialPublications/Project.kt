package Aequivaleo_OfficialPublications

import Aequivaleo_OfficialPublications.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Aequivaleo_OfficialPublications")
    name = "Official Publications"
    description = "Holds projects and builds related to official publications"

    buildType(Aequivaleo_OfficialPublications_CommonB)
})
