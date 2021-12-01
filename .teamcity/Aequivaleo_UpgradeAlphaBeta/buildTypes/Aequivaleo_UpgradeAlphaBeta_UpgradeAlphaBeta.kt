package Aequivaleo_UpgradeAlphaBeta.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule

object Aequivaleo_UpgradeAlphaBeta_UpgradeAlphaBeta : BuildType({
    templates(_Self.buildTypes.Upgrade)
    name = "Upgrade - Alpha -> Beta"
    description = "Upgrades the current Alpha to Beta."

    params {
        text("Source.Branch", "version", label = "Source branch type", description = "The source branch type for the upgrade. EG: version or testing", allowEmpty = false)
        param("Default.Branch", "testing/latest")
        param("VCS.Branches", "+:refs/heads/testing/(*)")
        text("Target.Branch", "testing", label = "Target branch type", description = "The target branch type for the upgrade. EG: testing or release.", allowEmpty = false)
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%build.counter%-BETA", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    steps {
        script {
            name = "Fetch"
            id = "RUNNER_80"
            scriptContent = "git pull"
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
        stepsOrder = arrayListOf("RUNNER_81", "RUNNER_80", "RUNNER_78", "RUNNER_79")
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
