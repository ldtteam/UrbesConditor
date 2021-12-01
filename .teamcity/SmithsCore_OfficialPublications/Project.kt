package SmithsCore_OfficialPublications

import SmithsCore_OfficialPublications.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("SmithsCore_OfficialPublications")
    name = "Official Publications"
    description = "Holds projects and builds related to official publications"
    archived = true

    buildType(SmithsCore_OfficialPublications_CommonBuildCounter)
})
