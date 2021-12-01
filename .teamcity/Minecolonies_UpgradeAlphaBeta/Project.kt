package Minecolonies_UpgradeAlphaBeta

import Minecolonies_UpgradeAlphaBeta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Minecolonies_UpgradeAlphaBeta")
    name = "Upgrade - Alpha -> Beta"
    description = "Updates the current alpha to beta."

    buildType(Minecolonies_Beta_UpgradeAlphaBeta)
})
