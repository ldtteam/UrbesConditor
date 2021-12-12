package patches.projects

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubIssues
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the project with id = 'Structurize'
accordingly, and delete the patch script.
*/
changeProject(RelativeId("Structurize")) {
    check(description == "Structure based world modification using creative wants.") {
        "Unexpected description: '$description'"
    }
    description = ""

    params {
        remove {
            param("Current Minecraft Version", "1.16")
        }
        remove {
            text("Repository", "ldtteam/structurize", label = "Repository", description = "The repository for minecolonies.", readOnly = true, allowEmpty = true)
        }
        remove {
            param("Upsource.Project.Id", "structurize")
        }
        remove {
            text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%env.Version.Patch%%env.Version.Suffix%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        }
        remove {
            param("env.Version.Major", "0")
        }
        remove {
            param("env.Version.Minor", "13")
        }
        remove {
            param("env.Version.Patch", "0")
        }
        remove {
            param("env.Version.Suffix", "")
        }
        remove {
            param("env.crowdinKey", "credentialsJSON:444bd785-791b-42ae-9fae-10ee93a2fbd3")
        }
    }

    features {
        remove(0) {
            githubIssues {
                id = "PROJECT_EXT_26"
                displayName = "ldtteam/minecolonies"
                repositoryURL = "https://github.com/ldtteam/minecolonies"
                authType = accessToken {
                    accessToken = "credentialsJSON:47381468-aceb-4992-93c9-1ccd4d7aa67f"
                }
            }
        }
    }

    expectSubProjectsOrder(RelativeId("Structurize_Release"), RelativeId("Structurize_UpgradeBetaRelease"), RelativeId("Structurize_Beta"), RelativeId("Structurize_UpgradeAlphaBeta"), RelativeId("Structurize_Alpha"), RelativeId("Structurize_OfficialPublications"), RelativeId("Structurize_Branches"), RelativeId("Structurize_PullRequests2"))
    subProjectsOrder = arrayListOf<Id>()
}
