package Serverpacklocator.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object Serverpacklocator_Build : BuildType({
    name = "Build"

    vcs {
        root(Serverpacklocator.vcsRoots.Serverpacklocator_HttpsGithubComOrionDevelopmentServerpacklocatorRefsHeadsMaster)
    }

    steps {
        gradle {
            tasks = "clean build publish"
            useGradleWrapper = false
            dockerImage = "gradle:7.2-jdk16"
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            param("org.jfrog.artifactory.selectedDeployableServer.urlId", "2")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*")
            param("org.jfrog.artifactory.selectedDeployableServer.resolvingRepo", "modding")
            param("org.jfrog.artifactory.selectedDeployableServer.targetRepo", "mods-maven")
        }
    }

    triggers {
        vcs {
        }
    }
})
