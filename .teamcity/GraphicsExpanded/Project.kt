package GraphicsExpanded

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("GraphicsExpanded")
    name = "Graphics Expanded"
    description = "The OpenGL Graphics library for the LDTTeam"

    params {
        param("Current Minecraft Version", "latest")
        text("Repository", "ldtteam/graphicsexpanded", label = "Repository", description = "The repository for minecolonies.", allowEmpty = true)
        param("env.Version.Minor", "10")
        param("Project.Type", "libraries")
        param("env.Version.Patch", "0")
        param("Upsource.Project.Id", "graphicsexpanded")
        param("env.Version.Suffix", "")
        param("env.Version.Major", "0")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_29"
            displayName = "ldtteam/minecolonies"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("GraphicsExpanded_Release"), RelativeId("GraphicsExpanded_UpgradeBetaRelease"), RelativeId("GraphicsExpanded_Beta"), RelativeId("GraphicsExpanded_UpgradeAlphaBeta"), RelativeId("GraphicsExpanded_Alpha"), RelativeId("GraphicsExpanded_OfficialPublications"), RelativeId("GraphicsExpanded_Branches"), RelativeId("GraphicsExpanded_PullRequests"))

    subProject(GraphicsExpanded_Branches.Project)
    subProject(GraphicsExpanded_OfficialPublications.Project)
    subProject(GraphicsExpanded_Alpha.Project)
    subProject(GraphicsExpanded_Release.Project)
    subProject(GraphicsExpanded_Beta.Project)
    subProject(GraphicsExpanded_PullRequests.Project)
    subProject(GraphicsExpanded_UpgradeAlphaBeta.Project)
    subProject(GraphicsExpanded_UpgradeBetaRelease.Project)
})
