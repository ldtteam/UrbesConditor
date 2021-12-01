package Armory_Weaponry_UpgradeAlphaBeta

import Armory_Weaponry_UpgradeAlphaBeta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Armory_Weaponry_UpgradeAlphaBeta")
    name = "Upgrade - Alpha -> Beta"
    description = "Updates the current alpha to beta."
    archived = true

    buildType(Armory_Weaponry_UpgradeAlphaBeta_UpgradeAlphaBeta)
})
