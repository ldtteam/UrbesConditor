package MinecoloniesWiki.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dockerCommand

object MinecoloniesWiki_BuildAndPublish : BuildType({
    name = "Build and Publish"

    artifactRules = "+:docker-compose.yml"

    vcs {
        root(MinecoloniesWiki.vcsRoots.MinecoloniesWiki_HttpsGithubComLdtteamMinecoloniesWikiRefsHeadsMain)
    }

    steps {
        dockerCommand {
            name = "Docker Build"
            enabled = false
            commandType = build {
                source = file {
                    path = "Dockerfile"
                }
                namesAndTags = """
                    %env.DOCKER_REGISTRY%/ldtteam/minecolonies/wiki:latest
                    %env.DOCKER_REGISTRY%/ldtteam/minecolonies/wiki:%build.number%
                """.trimIndent()
                commandArgs = "--pull"
            }
        }
        dockerCommand {
            name = "Docker Login"
            enabled = false
            commandType = other {
                subCommand = "login"
                commandArgs = "-u %env.DOCKER_USERNAME% -p %env.DOCKER_PASSWORD% %env.DOCKER_REGISTRY%"
            }
        }
        dockerCommand {
            name = "Docker Push"
            enabled = false
            commandType = push {
                namesAndTags = """
                    %env.DOCKER_REGISTRY%/ldtteam/minecolonies/wiki:latest
                    %env.DOCKER_REGISTRY%/ldtteam/minecolonies/wiki:%build.counter%
                """.trimIndent()
            }
        }
        dockerCommand {
            name = "Docker Logout"
            enabled = false
            executionMode = BuildStep.ExecutionMode.ALWAYS
            commandType = other {
                subCommand = "logout"
            }
        }
    }
})
