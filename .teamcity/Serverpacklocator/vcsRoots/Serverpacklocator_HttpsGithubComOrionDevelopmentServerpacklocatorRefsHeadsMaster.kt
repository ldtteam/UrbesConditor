package Serverpacklocator.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object Serverpacklocator_HttpsGithubComOrionDevelopmentServerpacklocatorRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/OrionDevelopment/serverpacklocator#refs/heads/master"
    url = "https://github.com/OrionDevelopment/serverpacklocator"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "OrionDevelopment"
        password = "credentialsJSON:23cd3823-3e9f-4c80-a1c4-6ad9c876842a"
    }
})
