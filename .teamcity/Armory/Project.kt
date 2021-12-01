package Armory

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Armory")
    name = "Armory"
    description = "Armory. The minecraft armor mod."
    archived = true

    params {
        param("Repository", "ldtteam/armory")
        param("env.Version.Minor", "10")
        param("env.Version.Patch", "0")
        param("Upsource.Project.Id", "armory")
        param("env.Version.Suffix", "")
        param("env.Version.Major", "0")
        param("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%")
    }

    subProject(Core.Project)
    subProject(Armory_Weaponry.Project)
})
