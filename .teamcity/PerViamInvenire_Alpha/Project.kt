package PerViamInvenire_Alpha

import PerViamInvenire_Alpha.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("PerViamInvenire_Alpha")
    name = "Alpha"
    description = "Alpha version builds of PerViamInvenire"

    buildType(PerViamInvenire_Alpha_Release)

    params {
        param("Default.Branch", "version/latest")
        param("VCS.Branches", "+:refs/heads/version/(*)")
        param("env.CURSERELEASETYPE", "alpha")
        param("env.Version.Suffix", "-ALPHA")
    }
})
