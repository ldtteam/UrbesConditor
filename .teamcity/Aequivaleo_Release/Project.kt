package Aequivaleo_Release

import Aequivaleo_Release.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("Aequivaleo_Release")
    name = "Release"
    description = "Release version builds of Aquivaleo"

    buildType(Aequivaleo_Release_Release)

    params {
        password("env.crowdinKey", "credentialsJSON:be67336c-4ed1-464c-b531-92270ba39b53", label = "Crowdin key", description = "The API key for getting crowdin translations")
        param("Default.Branch", "release/%Current Minecraft Version%")
        param("VCS.Branches", "+:refs/heads/release/(*)")
        param("env.CURSERELEASETYPE", "release")
        param("env.Version.Suffix", "-RELEASE")
    }
})
