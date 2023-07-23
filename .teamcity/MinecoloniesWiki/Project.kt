package MinecoloniesWiki

import MinecoloniesWiki.buildTypes.*
import MinecoloniesWiki.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("MinecoloniesWiki")
    name = "Minecolonies - Wiki - Old"
    archived = true

    vcsRoot(MinecoloniesWiki_HttpsGithubComLdtteamMinecoloniesWikiRefsHeadsMain)

    buildType(MinecoloniesWiki_Deploy)
    buildType(MinecoloniesWiki_BuildAndPublish)
})
