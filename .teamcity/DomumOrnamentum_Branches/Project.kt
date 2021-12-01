package DomumOrnamentum_Branches

import DomumOrnamentum_Branches.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("DomumOrnamentum_Branches")
    name = "Branches"
    description = "All none release branches."

    buildType(DomumOrnamentum_Branches_Common)
    buildType(DomumOrnamentum_Branches_Build)

    params {
        text("Default.Branch", "CI/Default", label = "Default branch", description = "The default branch for branch builds", readOnly = true, allowEmpty = true)
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
