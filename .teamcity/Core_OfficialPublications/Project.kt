package Core_OfficialPublications

import Core_OfficialPublications.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Core_OfficialPublications")
    name = "Official Publications"
    description = "Holds projects and builds related to official publications"
    archived = true

    buildType(Core_OfficialPublications_CommonB)
})
