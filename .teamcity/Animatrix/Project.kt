package Animatrix

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("Animatrix")
    name = "Animatrix"
    description = "The skeleton based modelling and animation library for minecraft."

    params {
        text("Repository", "ldtteam/animatrix", label = "Repository", description = "The repository for minecolonies.", allowEmpty = true)
        param("env.Version.Minor", "10")
        param("Project.Type", "libraries")
        param("env.Version.Patch", "0")
        param("Upsource.Project.Id", "animatrix")
        param("env.Version.Suffix", "")
        param("env.Version.Major", "0")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_27"
            displayName = "ldtteam/minecolonies"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("Animatrix_Release"), RelativeId("Animatrix_UpgradeBetaRelease"), RelativeId("Animatrix_Beta"), RelativeId("Animatrix_UpgradeAlphaBeta"), RelativeId("Animatrix_Alpha"), RelativeId("Animatrix_OfficialPublications"), RelativeId("Animatrix_Branches"), RelativeId("Animatrix_PullRequests2"))

    subProject(Animatrix_Release.Project)
    subProject(Animatrix_UpgradeAlphaBeta.Project)
    subProject(Animatrix_Alpha.Project)
    subProject(Animatrix_PullRequests2.Project)
    subProject(Animatrix_Branches.Project)
    subProject(Animatrix_OfficialPublications.Project)
    subProject(Animatrix_Beta.Project)
    subProject(Animatrix_UpgradeBetaRelease.Project)
})
