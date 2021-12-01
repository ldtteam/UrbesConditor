package DataGenerators_UpgradeBetaRelease

import DataGenerators_UpgradeBetaRelease.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("DataGenerators_UpgradeBetaRelease")
    name = "Upgrade Beta -> Release"
    description = "Upgrades the current Beta to Release"

    buildType(DataGenerators_UpgradeBetaRelease_UpgradeBetaRelease)
})
