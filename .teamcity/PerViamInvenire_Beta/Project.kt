package PerViamInvenire_Beta

import PerViamInvenire_Beta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("PerViamInvenire_Beta")
    name = "Beta"
    description = "Beta version builds of PerViamInvenire"

    buildType(PerViamInvenire_Beta_Release)

    params {
        param("Default.Branch", "testing/latest")
        param("VCS.Branches", "+:refs/heads/testing/(*)")
        param("env.CURSERELEASETYPE", "beta")
        param("env.Version.Suffix", "-BETA")
    }
})
