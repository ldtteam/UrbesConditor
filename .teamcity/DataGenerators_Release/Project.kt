package DataGenerators_Release

import DataGenerators_Release.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("DataGenerators_Release")
    name = "Release"
    description = "Release version builds of DataGenerators"

    buildType(DataGenerators_Release_Release)

    params {
        param("Default.Branch", "release/%Current Minecraft Version%")
        param("VCS.Branches", "+:refs/heads/release/(*)")
        param("env.CURSERELEASETYPE", "release")
        param("env.Version.Suffix", "-RELEASE")
    }
})
