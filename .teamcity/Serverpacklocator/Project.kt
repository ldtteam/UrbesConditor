package Serverpacklocator

import Serverpacklocator.buildTypes.*
import Serverpacklocator.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Serverpacklocator")
    name = "Serverpacklocator"

    vcsRoot(Serverpacklocator_HttpsGithubComOrionDevelopmentServerpacklocatorRefsHeadsMaster)

    buildType(Serverpacklocator_Build)
})
