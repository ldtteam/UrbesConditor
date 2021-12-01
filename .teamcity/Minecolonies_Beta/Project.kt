package Minecolonies_Beta

import Minecolonies_Beta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Minecolonies_Beta")
    name = "Beta"
    description = "Beta version builds of minecolonies"

    buildType(Minecolonies_Beta_Release)

    params {
        password("env.crowdinKey", "credentialsJSON:be67336c-4ed1-464c-b531-92270ba39b53", label = "Crowdin key", description = "The API for getting the crowdin translations")
        param("Default.Branch", "testing/%Current Minecraft Version%")
        param("VCS.Branches", "+:refs/heads/testing/(*)")
        param("env.CURSERELEASETYPE", "beta")
        param("env.Version.Suffix", "-BETA")
    }
})
