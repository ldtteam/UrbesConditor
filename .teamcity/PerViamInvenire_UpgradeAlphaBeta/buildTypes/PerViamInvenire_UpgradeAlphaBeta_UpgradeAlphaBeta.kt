package PerViamInvenire_UpgradeAlphaBeta.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object PerViamInvenire_UpgradeAlphaBeta_UpgradeAlphaBeta : BuildType({
    templates(_Self.buildTypes.Upgrade)
    name = "Upgrade - Alpha -> Beta"
    description = "Upgrades the current Alpha to Beta."

    params {
        text("Source.Branch", "version", label = "Source branch type", description = "The source branch type for the upgrade. EG: version or testing", allowEmpty = false)
        text("Default.Branch", "testing/%Current Minecraft Version%", label = "Default branch", description = "The default branch of this build.", allowEmpty = true)
        param("VCS.Branches", "+:refs/heads/testing/(*)")
        text("Target.Branch", "testing", label = "Target branch type", description = "The target branch type for the upgrade. EG: testing or release.", allowEmpty = false)
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%build.counter%-BETA", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }
    
    disableSettings("BUILD_EXT_9")
})
