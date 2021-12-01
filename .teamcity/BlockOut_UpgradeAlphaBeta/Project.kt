package BlockOut_UpgradeAlphaBeta

import BlockOut_UpgradeAlphaBeta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("BlockOut_UpgradeAlphaBeta")
    name = "Upgrade - Alpha -> Beta"
    description = "Updates the current alpha to beta."

    buildType(BlockOut_UpgradeAlphaBeta_UpgradeAlphaBeta)
})
