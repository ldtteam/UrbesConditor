package Core_UpgradeAlphaBeta

import Core_UpgradeAlphaBeta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Core_UpgradeAlphaBeta")
    name = "Upgrade - Alpha -> Beta"
    description = "Updates the current alpha to beta."
    archived = true

    buildType(Core_UpgradeAlphaBeta_UpgradeAlphaBeta)
})
