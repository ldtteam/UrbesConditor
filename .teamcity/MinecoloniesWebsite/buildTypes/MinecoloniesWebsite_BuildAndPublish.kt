package MinecoloniesWebsite.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dockerCommand

object MinecoloniesWebsite_BuildAndPublish : BuildType({
    name = "Build and Publish"
    paused = true

    vcs {
        root(MinecoloniesWebsite.vcsRoots.MinecoloniesWebsite_HttpsGithubComLdtteamMinecoloniesWebsiteRefsHeadsMain)
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
})
