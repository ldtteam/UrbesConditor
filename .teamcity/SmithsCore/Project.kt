package SmithsCore

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("SmithsCore")
    name = "SmithsCore"
    description = "The SmithsCore minecraft library."
    archived = true

    params {
        text("Repository", "ldtteam/smithscore", label = "Repository", description = "The repository for smithscore.", allowEmpty = true)
        param("env.Version.Minor", "20")
        param("Project.Type", "libraries")
        param("env.Version.Patch", "0")
        param("Upsource.Project.Id", "smithscore")
        param("env.Version.Suffix", "")
        param("env.Version.Major", "0")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_16"
            displayName = "ldtteam/minecolonies"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("SmithsCore_Release"), RelativeId("SmithsCore_UpgradeBetaRelease"), RelativeId("SmithsCore_Beta"), RelativeId("SmithsCore_UpgradeAlphaBeta"), RelativeId("SmithsCore_Alpha"), RelativeId("SmithsCore_OfficialPublications"), RelativeId("SmithsCore_Branches"), RelativeId("SmithsCore_PullRequests"), RelativeId("SmithsCore_PullRequestsArchived"))

    subProject(SmithsCore_OfficialPublications.Project)
    subProject(SmithsCore_PullRequests.Project)
    subProject(SmithsCore_Release.Project)
    subProject(SmithsCore_UpgradeAlphaBeta.Project)
    subProject(SmithsCore_UpgradeBetaRelease.Project)
    subProject(SmithsCore_PullRequestsArchived.Project)
    subProject(SmithsCore_Beta.Project)
    subProject(SmithsCore_Alpha.Project)
    subProject(SmithsCore_Branches.Project)
})
