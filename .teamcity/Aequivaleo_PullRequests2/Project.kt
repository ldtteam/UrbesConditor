package Aequivaleo_PullRequests2

import Aequivaleo_PullRequests2.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Aequivaleo_PullRequests2")
    name = "Pull Requests"
    description = "All open pull requests"

    buildType(Aequivaleo_PullRequests2_BuildAndTest)
    buildType(Aequivaleo_PullRequests2_CommonBuildCounter)

    params {
        text("Default.Branch", "ci/default", label = "Default branch", description = "The default branch for pull requests.", readOnly = true, allowEmpty = false)
        param("VCS.Branches", """
            -:refs/heads/*
            +:refs/pull/(*)/head
            -:refs/heads/(CI/*)
        """.trimIndent())
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%build.counter%-PR", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    cleanup {
        baseRule {
            all(days = 60)
        }
    }
})
