package BlockUI

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("BlockUI")
    name = "BlockUI"
    description = "XML Based UI Library for Minecraft"

    params {
        text("Repository", "ldtteam/blockui", label = "Repository", description = "The repository for PerViamInvenire.", allowEmpty = true)
        param("env.Version.Minor", "0")
        param("Project.Type", "libraries")
        param("env.Version.Patch", "0")
        param("Upsource.Project.Id", "blockui")
        param("env.Version.Suffix", "")
        param("env.Version.Major", "0")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_31"
            displayName = "ldtteam/minecolonies"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("BlockUI_Alpha"), RelativeId("BlockUI_OfficialPublications"), RelativeId("BlockUI_Branches"), RelativeId("BlockUI_PullRequests2"))

    subProject(BlockUI_PullRequests2.Project)
    subProject(BlockUI_Branches.Project)
    subProject(BlockUI_Alpha.Project)
    subProject(BlockUI_OfficialPublications.Project)
})
