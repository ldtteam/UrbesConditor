package PistonUnlimited.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object PistonUnlimited_Configuration : GitVcsRoot({
    name = "Configuration"
    url = "https://github.com/ldtteam/Piston-Unlimited.git"
    branch = "version/main"
    authMethod = password {
        userName = "git"
        password = "credentialsJSON:5da508d6-480f-42e8-88f9-24212a320d7a"
    }
})
