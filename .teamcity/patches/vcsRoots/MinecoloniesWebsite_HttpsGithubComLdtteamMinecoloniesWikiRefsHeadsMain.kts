package patches.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a vcsRoot with id = 'MinecoloniesWebsite_HttpsGithubComLdtteamMinecoloniesWikiRefsHeadsMain'
in the project with id = 'MinecoloniesWebsite', and delete the patch script.
*/
create(RelativeId("MinecoloniesWebsite"), GitVcsRoot({
    id("MinecoloniesWebsite_HttpsGithubComLdtteamMinecoloniesWikiRefsHeadsMain")
    name = "https://github.com/ldtteam/MinecoloniesWebsite#refs/heads/main"
    url = "https://github.com/ldtteam/MinecoloniesWebsite"
    branch = "refs/heads/main"
    checkoutPolicy = GitVcsRoot.AgentCheckoutPolicy.USE_MIRRORS
    authMethod = password {
        userName = "LDTTeam-Buildsystem"
        password = "credentialsJSON:0ab5d46b-ca88-4fa8-93d2-163fe135b15a"
    }
}))

