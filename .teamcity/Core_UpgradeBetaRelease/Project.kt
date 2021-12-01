package Core_UpgradeBetaRelease

import Core_UpgradeBetaRelease.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Core_UpgradeBetaRelease")
    name = "Upgrade Beta -> Release"
    description = "Upgrades the current Beta to Release"
    archived = true

    buildType(Core_UpgradeBetaRelease_UpgradeBetaRelease)
})
