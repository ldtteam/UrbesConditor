package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ideaInspections

object Analysis : Template({
    name = "Analysis"

    artifactRules = """+:build\libs\*.jar => build\libs"""
    buildNumberPattern = "%env.Version%"

    params {
        text("Repository", "Minecolonies/minecolonies", label = "Repostior", description = "The repository that this build targets.", allowEmpty = true)
        text("VCS.Branches", "+:refs/heads/(*)", label = "VCS Branches", description = "The VCS Branches this should run on.", allowEmpty = true)
        text("env.Version.Minor", "9", label = "Minor Version", description = "The minor version of the project",
              regex = "[0-9]+", validationMessage = "Please specify a number!")
        text("Analysis.Id", "", label = "Analysis ID", description = "Used to define the analysis incase of multiple analysis during a build.", allowEmpty = true)
        text("env.Version.Major", "0", label = "Major Version", description = "The major version of the project.",
              regex = "[0-9]+", validationMessage = "Please specificy a number!")
        text("Default.Branch", "CI/Default", label = "Default branch", description = "The default branch of this build.", readOnly = true, allowEmpty = true)
        text("Sources", "src/main/java", label = "Sources", description = "The sources the analysis should be run on.", allowEmpty = false)
        text("env.Version", "%env.Version.Major%.%env.Version.Minor%.%build.number%", label = "Version", description = "The version of the project.", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(_Self.vcsRoots.General)

        showDependenciesChanges = true
    }

    steps {
        ideaInspections {
            name = "Analysis"
            id = "RUNNER_54"
            pathToProject = "build.gradle"
            jvmArgs = "-Xmx512m -XX:ReservedCodeCacheSize=240m -Didea.analyze.scope=minecolonies_api"
            targetJdkHome = "%env.JDK_18%"
            ideaAppHome = "%teamcity.tool.intellij.DEFAULT%"
            includeExcludePatterns = "%Sources%"
            profileName = "Minecolonies"
            profilePath = "documentation/inspections/Minecolonies.xml"
        }
    }
})
