package SmithsCore_UpgradeAlphaBeta

import SmithsCore_UpgradeAlphaBeta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("SmithsCore_UpgradeAlphaBeta")
    name = "Upgrade - Alpha -> Beta"
    description = "Updates the current alpha to beta."
    archived = true

    buildType(SmithsCore_UpgradeAlphaBeta_UpgradeAlphaBeta)
})
