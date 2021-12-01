package SmithsCore_UpgradeBetaRelease

import SmithsCore_UpgradeBetaRelease.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("SmithsCore_UpgradeBetaRelease")
    name = "Upgrade Beta -> Release"
    description = "Upgrades the current Beta to Release"
    archived = true

    buildType(SmithsCore_UpgradeBetaRelease_UpgradeBetaRelease)
})
