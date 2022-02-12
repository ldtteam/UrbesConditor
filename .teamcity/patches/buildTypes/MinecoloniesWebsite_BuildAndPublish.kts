package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'MinecoloniesWebsite_BuildAndPublish'
in the project with id = 'MinecoloniesWebsite', and delete the patch script.
*/
create(RelativeId("MinecoloniesWebsite"), BuildType({
    id("MinecoloniesWebsite_BuildAndPublish")
    name = "Build and Publish"

    artifactRules = "+:docker-compose.yml"

    vcs {
        root(RelativeId("MinecoloniesWebsite_HttpsGithubComLdtteamMinecoloniesWikiRefsHeadsMain"))
    }

    steps {
        dockerCommand {
            name = "Docker Build"
            commandType = build {
                source = file {
                    path = "Dockerfile"
                }
                namesAndTags = """
                    %env.DOCKER_REGISTRY%/ldtteam/minecolonies/website:latest
                    %env.DOCKER_REGISTRY%/ldtteam/minecolonies/website:%build.number%
                """.trimIndent()
                commandArgs = "--pull"
            }
        }
        dockerCommand {
            name = "Docker Login"
            commandType = other {
                subCommand = "login"
                commandArgs = "-u %env.DOCKER_USERNAME% -p %env.DOCKER_PASSWORD% %env.DOCKER_REGISTRY%"
            }
        }
        dockerCommand {
            name = "Docker Push"
            commandType = push {
                namesAndTags = """
                    %env.DOCKER_REGISTRY%/ldtteam/minecolonies/website:latest
                    %env.DOCKER_REGISTRY%/ldtteam/minecolonies/website:%build.counter%
                """.trimIndent()
            }
        }
        dockerCommand {
            name = "Docker Logout"
            executionMode = BuildStep.ExecutionMode.ALWAYS
            commandType = other {
                subCommand = "logout"
            }
        }
    }

    features {
        dockerSupport {
            loginToRegistry = on {
                dockerRegistryId = "PROJECT_EXT_9"
            }
        }
    }
}))

