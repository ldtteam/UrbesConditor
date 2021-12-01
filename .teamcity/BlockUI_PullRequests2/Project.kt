package BlockUI_PullRequests2

import BlockUI_PullRequests2.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("BlockUI_PullRequests2")
    name = "Pull Requests"
    description = "All open pull requests"

    buildType(BlockUI_PullRequests2_CommonBuildCounter)
    buildType(BlockUI_PullRequests2_BuildAndTest)

    params {
        text("Default.Branch", "CI/Default", label = "Default branch", description = "The default branch for pull requests.", readOnly = true, allowEmpty = false)
        param("VCS.Branches", """
            -:refs/heads/*
            +:refs/pull/(*)/head
            -:refs/heads/(CI/*)
        """.trimIndent())
        param("env.Version.Suffix", "-PR")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%build.counter%-PR", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    cleanup {
        baseRule {
            all(days = 60)
        }
    }
})
