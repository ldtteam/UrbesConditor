package DataGenerators_Beta

import DataGenerators_Beta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("DataGenerators_Beta")
    name = "Beta"
    description = "Beta version builds of DataGenerators"

    buildType(DataGenerators_Beta_Release)

    params {
        param("Default.Branch", "testing/%Current Minecraft Version%")
        param("VCS.Branches", "+:refs/heads/testing/(*)")
        param("env.CURSERELEASETYPE", "beta")
        param("env.Version.Suffix", "-BETA")
    }
})
