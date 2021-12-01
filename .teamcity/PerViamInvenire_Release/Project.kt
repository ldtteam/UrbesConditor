package PerViamInvenire_Release

import PerViamInvenire_Release.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("PerViamInvenire_Release")
    name = "Release"
    description = "Release version builds of PerViamInvenire"

    buildType(PerViamInvenire_Release_Release)

    params {
        param("Default.Branch", "release/latest")
        param("VCS.Branches", "+:refs/heads/release/(*)")
        param("env.CURSERELEASETYPE", "release")
        param("env.Version.Suffix", "-RELEASE")
    }
})
