package GraphicsExpanded_OfficialPublications

import GraphicsExpanded_OfficialPublications.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("GraphicsExpanded_OfficialPublications")
    name = "Official Publications"
    description = "Holds projects and builds related to official publications"

    buildType(GraphicsExpanded_OfficialPublications_CommonBuildCounter)
})
