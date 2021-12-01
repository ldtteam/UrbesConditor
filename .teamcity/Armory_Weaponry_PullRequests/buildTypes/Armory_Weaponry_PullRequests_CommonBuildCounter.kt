package Armory_Weaponry_PullRequests.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Armory_Weaponry_PullRequests_CommonBuildCounter : BuildType({
    templates(_Self.buildTypes.CommonBuildCounter)
    name = "Common Build Counter"
    description = "Defines version numbers uniquely over all Pull Request builds"
    paused = true
})
