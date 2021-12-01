package JVoxelizer_UpgradeBetaRelease

import JVoxelizer_UpgradeBetaRelease.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("JVoxelizer_UpgradeBetaRelease")
    name = "Upgrade Beta -> Release"
    description = "Upgrades the current Beta to Release"
    archived = true

    buildType(JVoxelizer_UpgradeBetaRelease_UpgradeBetaRelease)
})
