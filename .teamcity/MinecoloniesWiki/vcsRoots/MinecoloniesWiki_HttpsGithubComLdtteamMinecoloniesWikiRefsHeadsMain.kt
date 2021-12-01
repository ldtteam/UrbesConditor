package MinecoloniesWiki.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object MinecoloniesWiki_HttpsGithubComLdtteamMinecoloniesWikiRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/ldtteam/MinecoloniesWiki#refs/heads/main"
    url = "https://github.com/ldtteam/MinecoloniesWiki"
    branch = "refs/heads/main"
    authMethod = password {
        userName = "OrionDevelopment"
        password = "credentialsJSON:6131cd14-fa05-44e0-bcb6-d834103f967c"
    }
    param("useAlternates", "true")
})
