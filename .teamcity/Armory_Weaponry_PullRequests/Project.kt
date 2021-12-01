package Armory_Weaponry_PullRequests

import Armory_Weaponry_PullRequests.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Armory_Weaponry_PullRequests")
    name = "Pull Requests"
    description = "All open pull requests"
    archived = true

    buildType(Armory_Weaponry_PullRequests_CommonBuildCounter)
    buildType(Armory_Weaponry_PullRequests_BuildAndTest)

    params {
        text("Default.Branch", "CI/Default", label = "Default branch", description = "The default branch for pull requests.", readOnly = true, allowEmpty = false)
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
