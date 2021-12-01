package Aequivaleo

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("Aequivaleo")
    name = "Aequivaleo"
    description = "The mediator library for alchemy mods."

    params {
        param("Current Minecraft Version", "latest")
        text("Repository", "ldtteam/aequivaleo", label = "Repository", description = "The repository for the project.", readOnly = true, allowEmpty = true)
        param("env.Version.Minor", "1")
        param("Project.Type", "mods")
        param("env.Version.Patch", "0")
        param("Upsource.Project.Id", "aequivaleo")
        param("env.Version.Suffix", "")
        param("env.Version.Major", "0")
        param("filename.prefix", "aequivaleo")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_12"
            displayName = "ldtteam/minecolonies"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("Aequivaleo_Release"), RelativeId("Aequivaleo_UpgradeBetaRelease"), RelativeId("Aequivaleo_Beta"), RelativeId("Aequivaleo_UpgradeAlphaBeta"), RelativeId("Aequivaleo_Alpha"), RelativeId("Aequivaleo_OfficialPublications"), RelativeId("Aequivaleo_Branches"), RelativeId("Aequivaleo_PullRequests2"))

    subProject(Aequivaleo_OfficialPublications.Project)
    subProject(Aequivaleo_UpgradeAlphaBeta.Project)
    subProject(Aequivaleo_Branches.Project)
    subProject(Aequivaleo_Beta.Project)
    subProject(Aequivaleo_UpgradeBetaRelease.Project)
    subProject(Aequivaleo_Release.Project)
    subProject(Aequivaleo_Alpha.Project)
    subProject(Aequivaleo_PullRequests2.Project)
})
