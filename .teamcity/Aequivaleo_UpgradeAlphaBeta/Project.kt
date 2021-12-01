package Aequivaleo_UpgradeAlphaBeta

import Aequivaleo_UpgradeAlphaBeta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Aequivaleo_UpgradeAlphaBeta")
    name = "Upgrade - Alpha -> Beta"
    description = "Updates the current alpha to beta."

    buildType(Aequivaleo_UpgradeAlphaBeta_UpgradeAlphaBeta)
})
