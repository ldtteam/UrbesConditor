package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

object General : GitVcsRoot({
    name = "General"
    url = "https://github.com/%Repository%.git"
    branch = "refs/heads/%Default.Branch%"
    branchSpec = "%VCS.Branches%"
    checkoutPolicy = GitVcsRoot.AgentCheckoutPolicy.USE_MIRRORS
    authMethod = password {
        userName = "LDTTeam-Buildsystem"
        password = "credentialsJSON:4c85f6ea-7739-4bf6-9fd6-7f04d0e12414"
    }
})
