package Minecolonies_Alpha

import Minecolonies_Alpha.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Minecolonies_Alpha")
    name = "Alpha"
    description = "Alpha version builds of minecolonies"

    buildType(Minecolonies_Alpha_Release)

    params {
        text("env.crowdinKey", "credentialsJSON:56301564-fafc-4136-a281-a30adf2193fa", label = "Crowdin key", description = "The API key for crowdin to pull translations", allowEmpty = true)
        param("Default.Branch", "version/%Current Minecraft Version%")
        param("VCS.Branches", "+:refs/heads/version/(*)")
        param("env.CURSERELEASETYPE", "alpha")
        param("env.Version.Suffix", "-ALPHA")
    }
})
