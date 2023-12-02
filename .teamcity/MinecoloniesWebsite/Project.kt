package MinecoloniesWebsite

import MinecoloniesWebsite.buildTypes.MinecoloniesWebsite_BuildAndPublish
import MinecoloniesWebsite.buildTypes.MinecoloniesWebsite_Deploy
import MinecoloniesWebsite.vcsRoots.MinecoloniesWebsite_HttpsGithubComLdtteamMinecoloniesWebsiteRefsHeadsMain
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("MinecoloniesWebsite")
    name = "Minecolonies - Website"
    archived = true

    vcsRoot(MinecoloniesWebsite_HttpsGithubComLdtteamMinecoloniesWebsiteRefsHeadsMain)

    buildType(MinecoloniesWebsite_Deploy)
    buildType(MinecoloniesWebsite_BuildAndPublish)
})
