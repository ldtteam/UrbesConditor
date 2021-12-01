package DomumOrnamentum_OfficialPublications

import DomumOrnamentum_OfficialPublications.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("DomumOrnamentum_OfficialPublications")
    name = "Official Publications"
    description = "Holds projects and builds related to official publications"

    buildType(DomumOrnamentum_OfficialPublications_CommonB)
})
