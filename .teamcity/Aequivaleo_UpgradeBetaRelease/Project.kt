package Aequivaleo_UpgradeBetaRelease

import Aequivaleo_UpgradeBetaRelease.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Aequivaleo_UpgradeBetaRelease")
    name = "Upgrade Beta -> Release"
    description = "Upgrades the current Beta to Release"

    buildType(Aequivaleo_UpgradeBetaRelease_UpgradeBetaRelease)
})
