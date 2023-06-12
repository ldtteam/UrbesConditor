package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object CommonBuildCounter : Template({
    name = "Common Build Counter"
    description = "The common build counter for builds"

    steps {
        script {
            name = "Echo build number"
            id = "RUNNER_82"
            enabled = false
            scriptContent = "echo %build.number%"
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
    }
})
