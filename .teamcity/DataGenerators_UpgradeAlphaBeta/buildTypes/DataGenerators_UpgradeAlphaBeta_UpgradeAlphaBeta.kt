package DataGenerators_UpgradeAlphaBeta.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object DataGenerators_UpgradeAlphaBeta_UpgradeAlphaBeta : BuildType({
    templates(_Self.buildTypes.Upgrade)
    name = "Upgrade - Alpha -> Beta"
    description = "Upgrades the current Alpha to Beta."

    params {
        param("Source.Branch", "version")
        param("Default.Branch", "testing/%Current Minecraft Version%")
        param("VCS.Branches", "+:refs/heads/testing/(*)")
        param("Target.Branch", "testing")
        param("env.Version", "%env.Version.Major%.%env.Version.Minor%.%build.counter%-BETA")
    }
    
    disableSettings("BUILD_EXT_9")
})
