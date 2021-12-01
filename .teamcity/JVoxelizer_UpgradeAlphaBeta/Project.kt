package JVoxelizer_UpgradeAlphaBeta

import JVoxelizer_UpgradeAlphaBeta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("JVoxelizer_UpgradeAlphaBeta")
    name = "Upgrade - Alpha -> Beta"
    description = "Updates the current alpha to beta."
    archived = true

    buildType(JVoxelizer_UpgradeAlphaBeta_UpgradeAlphaBeta)
})
