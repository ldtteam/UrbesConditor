package BlockOut

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("BlockOut")
    name = "BlockOut"
    description = "The datadriven Minecraft gui framework."

    params {
        text("Repository", "ldtteam/blockout", label = "Repository", description = "The repository for minecolonies.", allowEmpty = true)
        param("env.Version.Minor", "0")
        param("Project.Type", "mods")
        param("env.Version.Patch", "0")
        param("Upsource.Project.Id", "blockout")
        param("env.Version.Suffix", "")
        param("env.Version.Major", "2")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_28"
            displayName = "ldtteam/minecolonies"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("BlockOut_Release"), RelativeId("BlockOut_UpgradeBetaRelease"), RelativeId("BlockOut_Beta"), RelativeId("BlockOut_UpgradeAlphaBeta"), RelativeId("BlockOut_Alpha"), RelativeId("BlockOut_OfficialPublications"), RelativeId("BlockOut_Branches"), RelativeId("BlockOut_PullRequests"))

    subProject(BlockOut_Alpha.Project)
    subProject(BlockOut_Release.Project)
    subProject(BlockOut_UpgradeBetaRelease.Project)
    subProject(BlockOut_OfficialPublications.Project)
    subProject(BlockOut_Branches.Project)
    subProject(BlockOut_Beta.Project)
    subProject(BlockOut_UpgradeAlphaBeta.Project)
    subProject(BlockOut_PullRequests.Project)
})
