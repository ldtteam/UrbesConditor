package Minecolonies_UpgradeAlphaBeta.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule

object Minecolonies_Beta_UpgradeAlphaBeta : BuildType({
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

    triggers {
        schedule {
            id = "TRIGGER_1"
            schedulingPolicy = weekly {
                timezone = "Europe/Berlin"
            }
            triggerBuild = always()
            param("revisionRuleBuildBranch", "<default>")
        }
    }
    
    disableSettings("BUILD_EXT_9")
})
