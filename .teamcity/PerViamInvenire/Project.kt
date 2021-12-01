package PerViamInvenire

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("PerViamInvenire")
    name = "PerViamInvenire"
    description = "Multithreaded pathfinding for Minecraft"

    params {
        param("gradle.version", "7.1.1")
        text("Repository", "ldtteam/PerViamInvenire", label = "Repository", description = "The repository for PerViamInvenire.", allowEmpty = true)
        param("env.Version.Minor", "0")
        param("Project.Type", "libraries")
        param("env.Version.Patch", "0")
        param("Upsource.Project.Id", "perviaminvenire")
        param("env.Version.Suffix", "")
        param("env.Version.Major", "0")
        param("jdk.version", "jdk16")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_7"
            displayName = "ldtteam/minecolonies"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("PerViamInvenire_Release"), RelativeId("PerViamInvenire_UpgradeBetaRelease"), RelativeId("PerViamInvenire_Beta"), RelativeId("PerViamInvenire_UpgradeAlphaBeta"), RelativeId("PerViamInvenire_Alpha"), RelativeId("PerViamInvenire_OfficialPublications"), RelativeId("PerViamInvenire_Branches"), RelativeId("PerViamInvenire_PullRequests2"))

    subProject(PerViamInvenire_UpgradeAlphaBeta.Project)
    subProject(PerViamInvenire_Alpha.Project)
    subProject(PerViamInvenire_Release.Project)
    subProject(PerViamInvenire_UpgradeBetaRelease.Project)
    subProject(PerViamInvenire_Branches.Project)
    subProject(PerViamInvenire_PullRequests2.Project)
    subProject(PerViamInvenire_Beta.Project)
    subProject(PerViamInvenire_OfficialPublications.Project)
})
