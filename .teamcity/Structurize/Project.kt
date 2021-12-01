package Structurize

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("Structurize")
    name = "Structurize"
    description = "Structure based world modification using creative wants."

    params {
        param("env.crowdinKey", "credentialsJSON:444bd785-791b-42ae-9fae-10ee93a2fbd3")
        param("Current Minecraft Version", "1.16")
        text("Repository", "ldtteam/structurize", label = "Repository", description = "The repository for minecolonies.", readOnly = true, allowEmpty = true)
        param("env.Version.Minor", "13")
        param("env.Version.Patch", "0")
        param("Upsource.Project.Id", "structurize")
        param("env.Version.Suffix", "")
        param("env.Version.Major", "0")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_26"
            displayName = "ldtteam/minecolonies"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("Structurize_Release"), RelativeId("Structurize_UpgradeBetaRelease"), RelativeId("Structurize_Beta"), RelativeId("Structurize_UpgradeAlphaBeta"), RelativeId("Structurize_Alpha"), RelativeId("Structurize_OfficialPublications"), RelativeId("Structurize_Branches"), RelativeId("Structurize_PullRequests2"))

    subProject(Structurize_UpgradeAlphaBeta.Project)
    subProject(Structurize_OfficialPublications.Project)
    subProject(Structurize_Beta.Project)
    subProject(Structurize_UpgradeBetaRelease.Project)
    subProject(Structurize_Alpha.Project)
    subProject(Structurize_Release.Project)
    subProject(Structurize_PullRequests2.Project)
    subProject(Structurize_Branches.Project)
})
