package Core_Beta

import Core_Beta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Core_Beta")
    name = "Beta"
    description = "Beta version builds of minecolonies"
    archived = true

    buildType(Core_Beta_Release)

    params {
        param("Default.Branch", "testing/%Current Minecraft Version%")
        param("VCS.Branches", "+:refs/heads/testing/(*)")
        param("env.CURSERELEASETYPE", "beta")
        param("env.Version.Suffix", "-BETA")
    }
})
