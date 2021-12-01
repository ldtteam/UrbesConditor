package BlockUI_Alpha

import BlockUI_Alpha.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("BlockUI_Alpha")
    name = "Alpha"
    description = "Alpha version builds of PerViamInvenire"

    buildType(BlockUI_Alpha_Release)

    params {
        param("Default.Branch", "version/latest")
        param("VCS.Branches", "+:refs/heads/version/(*)")
        param("env.CURSERELEASETYPE", "alpha")
        param("env.Version.Suffix", "-ALPHA")
    }
})
