package Core_Release

import Core_Release.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Core_Release")
    name = "Release"
    description = "Beta version builds of minecolonies"
    archived = true

    buildType(Core_Release_Release)

    params {
        param("Default.Branch", "release/%Current Minecraft Version%")
        param("VCS.Branches", "+:refs/heads/release/(*)")
        param("env.CURSERELEASETYPE", "release")
        param("env.Version.Suffix", "-RELEASE")
    }
})
