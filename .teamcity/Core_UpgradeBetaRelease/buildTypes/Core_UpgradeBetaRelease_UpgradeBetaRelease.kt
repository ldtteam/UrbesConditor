package Core_UpgradeBetaRelease.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Core_UpgradeBetaRelease_UpgradeBetaRelease : BuildType({
    templates(_Self.buildTypes.Upgrade)
    name = "Upgrade Beta -> Release"
    description = "Upgrades the current Beta to Release."
    paused = true

    params {
        param("Source.Branch", "testing")
        param("Default.Branch", "release/%Current Minecraft Version%")
        param("VCS.Branches", "+:refs/heads/release/(*)")
        param("Target.Branch", "release")
        param("env.Version", "%env.Version.Major%.%env.Version.Minor%.%build.counter%-RELEASE")
    }
})
