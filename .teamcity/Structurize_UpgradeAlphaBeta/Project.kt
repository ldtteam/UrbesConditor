package Structurize_UpgradeAlphaBeta

import Structurize_UpgradeAlphaBeta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Structurize_UpgradeAlphaBeta")
    name = "Upgrade - Alpha -> Beta"
    description = "Updates the current alpha to beta."

    buildType(Structurize_UpgradeAlphaBeta_UpgradeAlphaBeta)

    params {
        param("Current Minecraft Version", "1.16.3")
    }
})
