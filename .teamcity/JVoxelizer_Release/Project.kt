package JVoxelizer_Release

import JVoxelizer_Release.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("JVoxelizer_Release")
    name = "Release"
    description = "Full version releases of JVoxelizer"
    archived = true

    buildType(JVoxelizer_Release_Release)

    params {
        param("Default.Branch", "release/%Current Minecraft Version%")
        param("VCS.Branches", "+:refs/heads/release/(*)")
        param("env.CURSERELEASETYPE", "release")
        param("env.Version.Suffix", "-RELEASE")
    }
})
