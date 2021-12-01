package Armory_Weaponry_Branches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Armory_Weaponry_Branches_CommonBuildCounter : BuildType({
    templates(_Self.buildTypes.CommonBuildCounter)
    name = "Common Build Counter"
    description = "Tracks the amount of builds run for branches"
    paused = true
})
