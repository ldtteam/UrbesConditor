package DomumOrnamentum_UpgradeBetaRelease

import DomumOrnamentum_UpgradeBetaRelease.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("DomumOrnamentum_UpgradeBetaRelease")
    name = "Upgrade Beta -> Release"
    description = "Upgrades the current Beta to Release"

    buildType(DomumOrnamentum_UpgradeBetaRelease_UpgradeBetaRelease)
})
