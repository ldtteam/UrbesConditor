package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object Upgrade : Template({
    name = "Upgrade"

    enablePersonalBuilds = false
    type = BuildTypeSettings.Type.DEPLOYMENT
    buildNumberPattern = "%env.Version%"
    maxRunningBuilds = 1

    params {
        text("Repository", "Minecolonies/minecolonies", label = "Repostior", description = "The repository that this build targets.", allowEmpty = true)
        param("VCS.Branches", "+:refs/heads/(*)")
        text("Target.Branch", "", label = "Target branch type", description = "The target branch type for the upgrade. EG: testing or release.", allowEmpty = false)
        text("env.Version.Minor", "1", label = "Minor Version", description = "The minor version of the project",
              regex = "[0-9]+", validationMessage = "Please specify a number!")
        text("Git.Push.Username", "LDTTeam-Buildsystem", label = "Username", description = "Username for the Git push. Can be different from the VCS username.", allowEmpty = false)
        password("Git.Push.Password", "credentialsJSON:0ab5d46b-ca88-4fa8-93d2-163fe135b15a", label = "Password", description = "Password for the Git Push. Can be different from the VCS password.")
        text("env.Version.Major", "1", label = "Major Version", description = "The major version of the project.",
              regex = "[0-9]+", validationMessage = "Please specificy a number!")
        text("Source.Branch", "", label = "Source branch type", description = "The source branch type for the upgrade. EG: version or testing", allowEmpty = false)
        text("Default.Branch", "CI/Default", label = "Default branch", description = "The default branch of this build.", allowEmpty = true)
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%build.number%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(_Self.vcsRoots.General)

        showDependenciesChanges = true
    }

    steps {
        script {
            name = "Configure Git"
            id = "RUNNER_81"
            scriptContent = """
                git config --global user.email "ldtbuildsystem@minecolonies.com"
                git config --global user.name "LDT-BuildSystem"
            """.trimIndent()
        }
        script {
            name = "Fetch"
            id = "RUNNER_80"
            scriptContent = "git pull --all"
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
        script {
            name = "Rebase"
            id = "RUNNER_78"
            scriptContent = "git rebase origin/%Source.Branch%/%teamcity.build.branch%"
        }
        script {
            name = "Push"
            id = "RUNNER_79"
            scriptContent = "git push https://%Git.Push.Username%:%Git.Push.Password%@github.com/%Repository%.git"
        }
    }
})
