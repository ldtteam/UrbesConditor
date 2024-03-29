package patches.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a vcsRoot with id = 'MinecoloniesWiki_2_HttpsGithubComLdtteamMinecoloniesWikiRefsHeadsMain'
in the project with id = 'MinecoloniesWiki_2', and delete the patch script.
*/
create(RelativeId("MinecoloniesWiki_2"), GitVcsRoot({
    id("MinecoloniesWiki_2_HttpsGithubComLdtteamMinecoloniesWikiRefsHeadsMain")
    name = "https://github.com/ldtteam/MinecoloniesWiki#refs/heads/main"
    url = "https://github.com/ldtteam/MinecoloniesWiki"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "OrionDevelopment"
        password = "credentialsJSON:23cd3823-3e9f-4c80-a1c4-6ad9c876842a"
    }
    param("oauthProviderId", "PROJECT_EXT_19")
}))

