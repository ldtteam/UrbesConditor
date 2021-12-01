package Core

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("Core")
    name = "Core"
    description = "The core Armory mod and its Api."
    archived = true

    features {
        githubIssues {
            id = "PROJECT_EXT_24"
            displayName = "ldtteam/minecolonies"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("Core_Release"), RelativeId("Core_UpgradeBetaRelease"), RelativeId("Core_Beta"), RelativeId("Core_UpgradeAlphaBeta"), RelativeId("Core_Alpha"), RelativeId("Core_OfficialPublications"), RelativeId("Core_Branches"), RelativeId("Core_PullRequests"))

    subProject(Core_Alpha.Project)
    subProject(Core_Release.Project)
    subProject(Core_UpgradeAlphaBeta.Project)
    subProject(Core_OfficialPublications.Project)
    subProject(Core_Beta.Project)
    subProject(Core_PullRequests.Project)
    subProject(Core_UpgradeBetaRelease.Project)
    subProject(Core_Branches.Project)
})
