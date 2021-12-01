package DataGenerators

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("DataGenerators")
    name = "DataGenerators"
    description = "The skeleton based modelling and animation library for minecraft."

    params {
        text("Repository", "ldtteam/datagenerators", label = "Repository", description = "The repository for minecolonies.", allowEmpty = true)
        param("env.Version.Minor", "1")
        param("Project.Type", "libraries")
        param("env.Version.Patch", "0")
        param("Upsource.Project.Id", "datagenerators")
        param("env.Version.Suffix", "")
        param("env.Version.Major", "0")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_1"
            displayName = "ldtteam/minecolonies"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("DataGenerators_Release"), RelativeId("DataGenerators_UpgradeBetaRelease"), RelativeId("DataGenerators_Beta"), RelativeId("DataGenerators_UpgradeAlphaBeta"), RelativeId("DataGenerators_Alpha"), RelativeId("DataGenerators_OfficialPublications"), RelativeId("DataGenerators_Branches"), RelativeId("DataGenerators_PullRequests2"))

    subProject(DataGenerators_UpgradeAlphaBeta.Project)
    subProject(DataGenerators_Branches.Project)
    subProject(DataGenerators_UpgradeBetaRelease.Project)
    subProject(DataGenerators_PullRequests2.Project)
    subProject(DataGenerators_Release.Project)
    subProject(DataGenerators_Beta.Project)
    subProject(DataGenerators_Alpha.Project)
    subProject(DataGenerators_OfficialPublications.Project)
})
