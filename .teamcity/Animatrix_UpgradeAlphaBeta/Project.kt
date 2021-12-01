package Animatrix_UpgradeAlphaBeta

import Animatrix_UpgradeAlphaBeta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Animatrix_UpgradeAlphaBeta")
    name = "Upgrade - Alpha -> Beta"
    description = "Updates the current alpha to beta."

    buildType(Animatrix_UpgradeAlphaBeta_UpgradeAlphaBeta)
})
