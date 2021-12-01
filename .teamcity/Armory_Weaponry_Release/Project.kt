package Armory_Weaponry_Release

import Armory_Weaponry_Release.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Armory_Weaponry_Release")
    name = "Release"
    description = "Beta version builds of armory weaponry"
    archived = true

    buildType(Armory_Weaponry_Release_Release)

    params {
        param("Default.Branch", "release/%Current Minecraft Version%")
        param("Repository", "ldtteam/weaponry")
        param("VCS.Branches", "+:refs/heads/release/(*)")
        param("env.CURSERELEASETYPE", "release")
        param("Upsource.Project.Id", "weaponry")
        param("env.Version.Suffix", "-RELEASE")
    }
})
