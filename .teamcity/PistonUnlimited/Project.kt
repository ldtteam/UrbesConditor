package PistonUnlimited

import PistonUnlimited.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("PistonUnlimited")
    name = "Piston Unlimited"
    description = "Structure based world modification using creative wants."

    vcsRoot(PistonUnlimited_Configuration)

    params {
        password("env.crowdinKey", "credentialsJSON:444bd785-791b-42ae-9fae-10ee93a2fbd3")
        select("Current Minecraft Version", "latest", label = "Current Minecraft Version",
                options = listOf("1.12", "1.13", "1.14", "1.15", "1.16", "1.17"))
        text("Repository", "ldtteam/Piston-Unlimited", label = "Repository", description = "The repository for minecolonies.", readOnly = true, allowEmpty = true)
        param("env.Version.Minor", "2")
        param("env.Version.Patch", "0")
        param("Upsource.Project.Id", "structurize")
        param("env.Version.Suffix", "")
        param("env.Version.Major", "1")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_36"
            displayName = "ldtteam/minecolonies"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("PistonUnlimited_Release"), RelativeId("PistonUnlimited_UpgradeBetaRelease"), RelativeId("PistonUnlimited_Beta"), RelativeId("PistonUnlimited_UpgradeAlphaBeta"), RelativeId("PistonUnlimited_Alpha"), RelativeId("PistonUnlimited_OfficialPublications"), RelativeId("PistonUnlimited_Branches"), RelativeId("PistonUnlimited_PullRequests2"))

    subProject(PistonUnlimited_OfficialPublications.Project)
    subProject(PistonUnlimited_UpgradeBetaRelease.Project)
    subProject(PistonUnlimited_Release.Project)
    subProject(PistonUnlimited_Branches.Project)
    subProject(PistonUnlimited_Beta.Project)
    subProject(PistonUnlimited_PullRequests2.Project)
    subProject(PistonUnlimited_Alpha.Project)
    subProject(PistonUnlimited_UpgradeAlphaBeta.Project)
})
