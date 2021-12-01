package GraphicsExpanded_UpgradeAlphaBeta

import GraphicsExpanded_UpgradeAlphaBeta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("GraphicsExpanded_UpgradeAlphaBeta")
    name = "Upgrade - Alpha -> Beta"
    description = "Updates the current alpha to beta."

    buildType(GraphicsExpanded_UpgradeAlphaBeta_UpgradeAlphaBeta)
})
