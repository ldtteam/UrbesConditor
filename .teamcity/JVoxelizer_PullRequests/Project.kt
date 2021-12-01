package JVoxelizer_PullRequests

import JVoxelizer_PullRequests.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("JVoxelizer_PullRequests")
    name = "Pull Requests"
    description = "All open pull requests"
    archived = true

    buildType(JVoxelizer_PullRequests_BuildAndTest)
    buildType(JVoxelizer_PullRequests_CommonBuildCounter)

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
