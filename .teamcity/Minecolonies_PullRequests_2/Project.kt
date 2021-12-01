package Minecolonies_PullRequests_2

import Minecolonies_PullRequests_2.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Minecolonies_PullRequests_2")
    name = "Pull Requests"
    description = "All open pull requests"

    buildType(Minecolonies_PullRequests_2_BuildAndTest)
    buildType(Minecolonies_PullRequests_2_CommonBuildCounter)

    params {
        text("Default.Branch", "version/%Current Minecraft Version%", label = "Default branch", description = "The default branch for pull requests.", allowEmpty = false)
        param("VCS.Branches", """
            -:refs/heads/*
            +:refs/pull/(*)/head
            -:refs/heads/(CI/*)
        """.trimIndent())
        param("env.Version", "%env.Version.Major%.%env.Version.Minor%.%build.counter%-PR")
    }

    cleanup {
        baseRule {
            all(days = 60)
        }
    }
})
