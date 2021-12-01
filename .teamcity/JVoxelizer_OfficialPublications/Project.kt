package JVoxelizer_OfficialPublications

import JVoxelizer_OfficialPublications.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("JVoxelizer_OfficialPublications")
    name = "Official Publications"
    description = "Holds projects and builds related to official publications"
    archived = true

    buildType(JVoxelizer_OfficialPublications_CommonBuildCounter)
})
