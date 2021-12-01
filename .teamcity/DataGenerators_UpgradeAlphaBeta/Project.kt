package DataGenerators_UpgradeAlphaBeta

import DataGenerators_UpgradeAlphaBeta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("DataGenerators_UpgradeAlphaBeta")
    name = "Upgrade - Alpha -> Beta"
    description = "Updates the current alpha to beta."

    buildType(DataGenerators_UpgradeAlphaBeta_UpgradeAlphaBeta)
})
