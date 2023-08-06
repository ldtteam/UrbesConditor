package MinecoloniesWiki.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.VcsTrigger
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object MinecoloniesWiki_Deploy : BuildType({
    name = "Deploy"
    description = "Deploys the wiki into the swarm"

    enablePersonalBuilds = false
    type = BuildTypeSettings.Type.DEPLOYMENT
    maxRunningBuilds = 1

    params {
        text("env.DOCKER_CERT_PATH", "/auth/docker", label = "Docker certificates path", description = "The path to the docker certificates on the agent that allow for authentication with the target docker host.", allowEmpty = true)
        text("env.DOCKER_HOST", "tcp://192.168.10.52:2376", label = "Docker host", description = "The docker host to deploy the target on.", allowEmpty = true)
        checkbox("env.DOCKER_TLS_VERIFY", "1", label = "Docker TLS Verify", description = "Indicator used to verifiy the remote servers TLS data.",
                  checked = "1", unchecked = "0")
    }

    steps {
        dockerCommand {
            name = "Docker Stack Deployment"
            commandType = other {
                subCommand = "stack"
                commandArgs = "deploy --prune --with-registry-auth -c docker-compose.yml minecolonies_wiki"
            }
        }
        dockerCommand {
            name = "Logout"
            executionMode = BuildStep.ExecutionMode.ALWAYS
            commandType = other {
                subCommand = "logout"
            }
        }
    }

    triggers {
        vcs {
            quietPeriodMode = VcsTrigger.QuietPeriodMode.USE_DEFAULT
            branchFilter = ""
            watchChangesInDependencies = true
        }
    }

    dependencies {
        dependency(MinecoloniesWiki_BuildAndPublish) {
            snapshot {
                reuseBuilds = ReuseBuilds.NO
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                artifactRules = "+:docker-compose.yml"
            }
        }
    }
})
