package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'Authentication_Deploy'
in the project with id = 'Authentication', and delete the patch script.
*/
create(RelativeId("Authentication"), BuildType({
    id("Authentication_Deploy")
    name = "Deploy"

    enablePersonalBuilds = false
    type = BuildTypeSettings.Type.DEPLOYMENT
    maxRunningBuilds = 1

    params {
        param("env.DOCKER_CERT_PATH", "/auth/docker")
        param("env.DOCKER_HOST", "tcp://192.168.10.52:2376")
        param("env.DOCKER_TLS_VERIFY", "1")
    }

    vcs {
        root(RelativeId("Authentication_HttpsGithubComLdtteamAuthenticationGitRefsHeadsMaster"))
    }

    steps {
        dockerCommand {
            name = "Deploy"
            commandType = other {
                subCommand = "stack"
                commandArgs = "deploy --prune --with-registry-auth -c docker-compose.yaml minecolonies_donator_auth"
            }
        }
    }

    features {
        dockerSupport {
            loginToRegistry = on {
                dockerRegistryId = "PROJECT_EXT_7"
            }
        }
    }

    dependencies {
        snapshot(RelativeId("Authentication_Build")) {
        }
    }
}))

