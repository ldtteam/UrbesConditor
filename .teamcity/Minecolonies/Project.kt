package Minecolonies

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("Minecolonies")
    name = "Minecolonies"
    description = "The Minecolonies Minecraft Mod"

    params {
        param("Current Minecraft Version", "main")
        text("Repository", "ldtteam/minecolonies", label = "Repository", description = "The repository for minecolonies.", readOnly = true, allowEmpty = true)
        param("env.Version.Minor", "0")
        param("Project.Type", "mods")
        param("env.Version.Patch", "0")
        param("Upsource.Project.Id", "minecolonies")
        param("env.Version.Suffix", "")
        param("env.Version.Major", "1")
        param("jdk.version", "jdk16")
        param("filename.prefix", "minecolonies")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_22"
            displayName = "ldtteam/minecolonies"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("Minecolonies_Release"), RelativeId("Minecolonies_UpgradeBetaRelease"), RelativeId("Minecolonies_Beta"), RelativeId("Minecolonies_UpgradeAlphaBeta"), RelativeId("Minecolonies_Alpha"), RelativeId("Minecolonies_OfficialPublications"), RelativeId("Minecolonies_Branches"), RelativeId("Minecolonies_PullRequests_2"), RelativeId("Minecolonies_PullRequests"))

    subProject(Minecolonies_Alpha.Project)
    subProject(Minecolonies_Beta.Project)
    subProject(Minecolonies_UpgradeAlphaBeta.Project)
    subProject(Minecolonies_OfficialPublications.Project)
    subProject(Minecolonies_Branches.Project)
    subProject(Minecolonies_Release.Project)
    subProject(Minecolonies_UpgradeBetaRelease.Project)
    subProject(Minecolonies_PullRequests_2.Project)
    subProject(Minecolonies_PullRequests.Project)
})
