package PistonUnlimited_UpgradeAlphaBeta

import PistonUnlimited_UpgradeAlphaBeta.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("PistonUnlimited_UpgradeAlphaBeta")
    name = "Upgrade - Alpha -> Beta"
    description = "Updates the current alpha to beta."

    buildType(PistonUnlimited_UpgradeAlphaBeta_UpgradeAlphaBeta)

    params {
        select("Current Minecraft Version", "1.16.3", label = "Current Minecraft Version",
                options = listOf("1.12", "1.13", "1.14", "1.15", "1.16", "1.17"))
    }
})
