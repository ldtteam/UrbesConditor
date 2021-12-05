package Structurize_Beta

import Structurize_Beta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Structurize_Beta")
    name = "Beta"
    description = "Beta version builds of minecolonies"

    buildType(Structurize_Beta_Release)

    params {
        param("gradle.version", "7.2")
        param("Default.Branch", "testing/%Current Minecraft Version%")
        param("VCS.Branches", "+:refs/heads/testing/(*)")
        param("env.CURSERELEASETYPE", "beta")
        param("jdk.version", "jdk16")
        param("env.Version.Suffix", "-BETA")
    }
})
