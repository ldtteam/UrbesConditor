package JVoxelizer_Branches

import JVoxelizer_Branches.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("JVoxelizer_Branches")
    name = "Branches"
    description = "Builds all none release branches of JVoxelizer."
    archived = true

    buildType(JVoxelizer_Branches_Build)
    buildType(JVoxelizer_Branches_CommonBuildCounter)

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
