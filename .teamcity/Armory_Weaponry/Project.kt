package Armory_Weaponry

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("Armory_Weaponry")
    name = "Weaponry"
    description = "The core Armory mod and its Api."
    archived = true

    params {
        param("Repository", "ldtteam/weaponry")
        param("Upsource.Project.Id", "weaponry")
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_18"
            displayName = "ldtteam/minecolonies"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("Armory_Weaponry_Release"), RelativeId("Armory_Weaponry_UpgradeBetaRelease"), RelativeId("Armory_Weaponry_Beta"), RelativeId("Armory_Weaponry_UpgradeAlphaBeta"), RelativeId("Armory_Weaponry_Alpha"), RelativeId("Armory_Weaponry_OfficialPublications"), RelativeId("Armory_Weaponry_Branches"), RelativeId("Armory_Weaponry_PullRequests"))

    subProject(Armory_Weaponry_UpgradeAlphaBeta.Project)
    subProject(Armory_Weaponry_Beta.Project)
    subProject(Armory_Weaponry_UpgradeBetaRelease.Project)
    subProject(Armory_Weaponry_Alpha.Project)
    subProject(Armory_Weaponry_Branches.Project)
    subProject(Armory_Weaponry_OfficialPublications.Project)
    subProject(Armory_Weaponry_Release.Project)
    subProject(Armory_Weaponry_PullRequests.Project)
})
