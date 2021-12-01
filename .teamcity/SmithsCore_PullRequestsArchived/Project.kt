package SmithsCore_PullRequestsArchived

import SmithsCore_PullRequestsArchived.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("SmithsCore_PullRequestsArchived")
    name = "Pull Requests (Archived)"
    description = "All open pull requests"
    archived = true

    buildType(SmithsCore_PullRequestsArchived_TestStructures)
    buildType(SmithsCore_PullRequestsArchived_CompileStructures)
    buildType(SmithsCore_PullRequestsArchived_Build)
    buildType(SmithsCore_PullRequestsArchived_Documentation)
    buildType(SmithsCore_PullRequestsArchived_AnalysisFull)
    buildType(SmithsCore_PullRequestsArchived_CompileBlockOut)
    buildType(SmithsCore_PullRequestsArchived_TestMain)
    buildType(SmithsCore_PullRequestsArchived_CompileMain)
    buildType(SmithsCore_PullRequestsArchived_TestApi)
    buildType(SmithsCore_PullRequestsArchived_TestBlockOut)
    buildType(SmithsCore_PullRequestsArchived_AnalysisPublish)
    buildType(SmithsCore_PullRequestsArchived_CompileApi)

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
    buildTypesOrder = arrayListOf(SmithsCore_PullRequestsArchived_Build, SmithsCore_PullRequestsArchived_Documentation, SmithsCore_PullRequestsArchived_AnalysisPublish, SmithsCore_PullRequestsArchived_AnalysisFull, SmithsCore_PullRequestsArchived_TestApi, SmithsCore_PullRequestsArchived_TestBlockOut, SmithsCore_PullRequestsArchived_TestMain, SmithsCore_PullRequestsArchived_TestStructures, SmithsCore_PullRequestsArchived_CompileApi, SmithsCore_PullRequestsArchived_CompileBlockOut, SmithsCore_PullRequestsArchived_CompileMain, SmithsCore_PullRequestsArchived_CompileStructures)
})
