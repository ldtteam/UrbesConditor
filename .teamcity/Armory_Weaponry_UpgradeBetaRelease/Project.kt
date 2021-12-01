package Armory_Weaponry_UpgradeBetaRelease

import Armory_Weaponry_UpgradeBetaRelease.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Armory_Weaponry_UpgradeBetaRelease")
    name = "Upgrade Beta -> Release"
    description = "Upgrades the current Beta to Release"
    archived = true

    buildType(Armory_Weaponry_UpgradeBetaRelease_UpgradeBetaRelease)
})
