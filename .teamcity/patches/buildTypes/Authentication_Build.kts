package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.DockerCommandStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'Authentication_Build'
in the project with id = 'Authentication', and delete the patch script.
*/
create(RelativeId("patches"), BuildType({
    id("Authentication_Build")
    name = "Build"

    vcs {
        root(RelativeId("Authentication_HttpsGithubComLdtteamAuthenticationGitRefsHeadsMaster"))
    }

    steps {
        dockerCommand {
            name = "Build"
            commandType = build {
                source = file {
                    path = "LDTTeam.Authentication.Server/Dockerfile"
                }
                contextDir = "."
                platform = DockerCommandStep.ImagePlatform.Linux
                namesAndTags = """
                    container.ldtteam.com/ldtteam/donator-auth:latest
                    container.ldtteam.com/ldtteam/donator-auth:%build.number%
                """.trimIndent()
            }
        }
        dockerCommand {
            name = "Push"
            commandType = push {
                namesAndTags = """
                    container.ldtteam.com/ldtteam/donator-auth:latest
                    container.ldtteam.com/ldtteam/donator-auth:%build.number%
                """.trimIndent()
            }
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        dockerSupport {
            loginToRegistry = on {
                dockerRegistryId = "PROJECT_EXT_7"
            }
        }
    }
}))

