package MinecoloniesWebsite.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object MinecoloniesWebsite_HttpsGithubComLdtteamMinecoloniesWebsiteRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/ldtteam/MinecoloniesWebsite#refs/heads/main"
    url = "https://github.com/ldtteam/MinecoloniesWebsite"
    branch = "refs/heads/main"
    authMethod = password {
        userName = "OrionDevelopment"
        password = "credentialsJSON:6131cd14-fa05-44e0-bcb6-d834103f967c"
    }
    param("useAlternates", "true")
})
