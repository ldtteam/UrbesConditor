package PerViamInvenire_UpgradeBetaRelease

import PerViamInvenire_UpgradeBetaRelease.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("PerViamInvenire_UpgradeBetaRelease")
    name = "Upgrade Beta -> Release"
    description = "Upgrades the current Beta to Release"

    buildType(PerViamInvenire_UpgradeBetaRelease_UpgradeBetaRelease)
})
