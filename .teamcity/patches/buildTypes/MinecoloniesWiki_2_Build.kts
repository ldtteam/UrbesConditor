package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'MinecoloniesWiki_2_Build'
in the project with id = 'MinecoloniesWiki_2', and delete the patch script.
*/
create(RelativeId("MinecoloniesWiki_2"), BuildType({
    id("MinecoloniesWiki_2_Build")
    name = "Build"

    vcs {
        root(RelativeId("MinecoloniesWiki_2_HttpsGithubComLdtteamMinecoloniesWikiRefsHeadsMain"))
    }

    steps {
        script {
            name = "Install Buildah"
            scriptContent = "apt update && apt install buildah -y"
        }
        script {
            name = "Build Container using Buildah"
            scriptContent = "buildah build -t %env.DOCKER_REGISTRY%/ldtteam/minecolonies/wiki:latest -t %env.DOCKER_REGISTRY%/ldtteam/minecolonies/wiki:%build.number% --pull --file Dockerfile"
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
}))
