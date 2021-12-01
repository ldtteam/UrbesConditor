package Minecolonies_PullRequests

import Minecolonies_PullRequests.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Minecolonies_PullRequests")
    name = "Pull Requests (Archived)"
    description = "All open pull requests"
    archived = true

    buildType(Minecolonies_PullRequests_CompileApi)
    buildType(Minecolonies_PullRequests_TestStructures)
    buildType(Minecolonies_PullRequests_CompileStructures)
    buildType(Minecolonies_PullRequests_AnalysisPublish)
    buildType(Minecolonies_PullRequests_TestMain)
    buildType(Minecolonies_PullRequests_TestBlockOut)
    buildType(Minecolonies_PullRequests_Build)
    buildType(Minecolonies_PullRequests_CompileBlockOut)
    buildType(Minecolonies_PullRequests_Documentation)
    buildType(Minecolonies_PullRequests_AnalysisFull)
    buildType(Minecolonies_PullRequests_CompileMain)
    buildType(Minecolonies_PullRequests_TestApi)

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
    buildTypesOrder = arrayListOf(Minecolonies_PullRequests_Build, Minecolonies_PullRequests_Documentation, Minecolonies_PullRequests_AnalysisPublish, Minecolonies_PullRequests_AnalysisFull, Minecolonies_PullRequests_TestApi, Minecolonies_PullRequests_TestBlockOut, Minecolonies_PullRequests_TestMain, Minecolonies_PullRequests_TestStructures, Minecolonies_PullRequests_CompileApi, Minecolonies_PullRequests_CompileBlockOut, Minecolonies_PullRequests_CompileMain, Minecolonies_PullRequests_CompileStructures)
})
