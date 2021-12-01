package Animatrix_UpgradeBetaRelease

import Animatrix_UpgradeBetaRelease.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Animatrix_UpgradeBetaRelease")
    name = "Upgrade Beta -> Release"
    description = "Upgrades the current Beta to Release"

    buildType(Animatrix_UpgradeBetaRelease_UpgradeBetaRelease)
})
