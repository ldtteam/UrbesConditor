package GraphicsExpanded_UpgradeBetaRelease

import GraphicsExpanded_UpgradeBetaRelease.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("GraphicsExpanded_UpgradeBetaRelease")
    name = "Upgrade Beta -> Release"
    description = "Upgrades the current Beta to Release"

    buildType(GraphicsExpanded_UpgradeBetaRelease_UpgradeBetaRelease)
})
