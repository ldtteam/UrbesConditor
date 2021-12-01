package Minecolonies_Branches

import Minecolonies_Branches.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Minecolonies_Branches")
    name = "Branches"
    description = "All none release branches."

    buildType(Minecolonies_Branches_Common)
    buildType(Minecolonies_Branches_Build)

    params {
        text("Default.Branch", "version/%Current Minecraft Version%", label = "Default branch", description = "The default branch for branch builds", allowEmpty = true)
        param("VCS.Branches", """
            +:refs/heads/(*)
            -:refs/heads/version/*
            -:refs/heads/testing/*
            -:refs/heads/release/*
            -:refs/pull/*/head
            -:refs/heads/CI/*
        """.trimIndent())
        param("env.Version.Suffix", "-PERSONAL")
    }

    cleanup {
        baseRule {
            all(days = 60)
        }
    }
})
