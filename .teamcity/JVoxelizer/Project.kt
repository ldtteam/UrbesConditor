package JVoxelizer

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues

object Project : Project({
    id("JVoxelizer")
    name = "JVoxelizer"
    description = "Version independent Minecraft modding Runtime."
    archived = true

    params {
        text("Repository", "ldtteam/jvoxelizer", label = "Repository", description = "The repository for JVoxelizer.", allowEmpty = true)
        param("env.Version.Minor", "0")
        param("Project.Type", "libraries")
        param("env.Version.Patch", "1")
        param("Upsource.Project.Id", "jvoxelizer")
        param("env.Version.Suffix", "")
        param("env.Version.Major", "1")
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    features {
        githubIssues {
            id = "PROJECT_EXT_25"
            displayName = "ldtteam/jvoxelizer"
            repositoryURL = "https://github.com/ldtteam/minecolonies"
            authType = accessToken {
                accessToken = "credentialsJSON:6131cd14-fa05-44e0-bcb6-d834103f967c"
            }
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("JVoxelizer_Release"), RelativeId("JVoxelizer_UpgradeBetaRelease"), RelativeId("JVoxelizer_Beta"), RelativeId("JVoxelizer_UpgradeAlphaBeta"), RelativeId("JVoxelizer_Alpha"), RelativeId("JVoxelizer_OfficialPublications"), RelativeId("JVoxelizer_Branches"), RelativeId("JVoxelizer_PullRequests"))

    subProject(JVoxelizer_Beta.Project)
    subProject(JVoxelizer_UpgradeBetaRelease.Project)
    subProject(JVoxelizer_Alpha.Project)
    subProject(JVoxelizer_Release.Project)
    subProject(JVoxelizer_OfficialPublications.Project)
    subProject(JVoxelizer_PullRequests.Project)
    subProject(JVoxelizer_Branches.Project)
    subProject(JVoxelizer_UpgradeAlphaBeta.Project)
})
