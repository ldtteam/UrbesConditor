package BlockOut_PullRequests

import BlockOut_PullRequests.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("BlockOut_PullRequests")
    name = "Pull Requests"
    description = "All open pull requests"

    buildType(BlockOut_PullRequests_CommonBuildCounter)
    buildType(BlockOut_PullRequests_BuildAndTest)

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
