package _Self

import _Self.buildTypes.*
import _Self.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.dockerRegistry

object Project : Project({
    description = "All projects of the Let's Dev Together team."

    vcsRoot(General)

    template(Documentation)
    template(Test)
    template(Minecolonies_PullRequests_2_DeployTestServer_2)
    template(Compile)
    template(Build)
    template(CommonBuildCounter)
    template(Analysis)
    template(DeployCurseForge)
    template(Upgrade)
    template(BuildWithRelease)
    template(BuildWithTesting)
    template(GenerateChangelog)

    params {
        password("env.CROWD_IN_API_KEY", "credentialsJSON:56fece8c-dce1-479e-b7da-403c37b05f99", label = "Crowdin API Key", display = ParameterDisplay.HIDDEN, readOnly = true)
        password("env.DOCKER_PASSWORD", "credentialsJSON:6d6b0058-18ff-4648-9393-a3ce7a3c66b1", label = "Docker password", description = "The password used to connect to the docker registry during docker operations.")
        param("env.JDK_VERSION", "%jdk.version%")
        password("env.crowdinKey", "credentialsJSON:be67336c-4ed1-464c-b531-92270ba39b53")
        password("GH.App.Id", "credentialsJSON:bc649fd5-86f3-49fb-917d-48cffdec42b9", display = ParameterDisplay.HIDDEN, readOnly = true)
        password("env.LDTTeamJfrogPassword", "credentialsJSON:fd8e76ea-f284-4185-8a02-f62a98048442", label = "JFrog Password", description = "The password for the LDTTeam JFrog Artifactory server.", display = ParameterDisplay.HIDDEN, readOnly = true)
        password("sonarqube.token", "credentialsJSON:3c063e2e-56d6-453d-9f0c-683da04017d3", display = ParameterDisplay.HIDDEN, readOnly = true)
        param("env.CROWD_IN_UPLOAD_WITH_FILTERED_BRANCHES_SPEC", "version/.*")
        text("env.DOCKER_USERNAME", "LDTTeamCICD", label = "Docker Username", description = "The username of the user used to connect to the docker registry during docker operations.", allowEmpty = true)
        param("env.CROWD_IN_BUILDING_WITH_FILTERED_BRANCHES_SPEC", "version/.*")
        text("env.DOCKER_REGISTRY", "container.ldtteam.com", label = "Docker Registry", description = "The Docker Registrry to use during docker operations.", allowEmpty = true)
        text("gradle.version", "7.3", label = "Gradle version", description = "The gradle versions used during build", allowEmpty = false)
        password("GH.App.Private.Key", "credentialsJSON:6f387e7f-6dc4-4485-b6dd-1ef16abf2eac", display = ParameterDisplay.HIDDEN)
        password("env.LDTTeamJfrogUsername", "credentialsJSON:4186bbc9-49cc-4c8e-ac44-69b26b1bc54e", label = "Username for LDTTeam JFrog", description = "The username of the LDTTeam JFrog Artifactory server.", display = ParameterDisplay.HIDDEN, readOnly = true)
        param("Default.Branch", "version/latest")
        param("env.GRADLE_VERSION", "%gradle.version%")
        text("jdk.version", "jdk17", label = "JDK Version", description = "The JDK Version used to build java projects.", allowEmpty = false)
    }

    features {
        dockerRegistry {
            id = "PROJECT_EXT_9"
            name = "LDTTeam Docker Container Registry"
            url = "https://container.ldtteam.com"
            userName = "LDTTeamCICD"
            password = "credentialsJSON:48666a0d-3b34-4243-8c9d-e1a9fb4d4148"
        }
    }
    subProjectsOrder = arrayListOf(RelativeId("Minecolonies"), RelativeId("Aequivaleo"), RelativeId("Structurize"), RelativeId("DomumOrnamentum"), RelativeId("PistonUnlimited"), RelativeId("BlockOut"), RelativeId("Animatrix"), RelativeId("PerViamInvenire"), RelativeId("BlockUI"), RelativeId("DataGenerators"), RelativeId("GraphicsExpanded"), RelativeId("MinecoloniesWiki"), RelativeId("Armory"), RelativeId("JVoxelizer"), RelativeId("SmithsCore"))

    subProject(BlockOut.Project)
    subProject(GraphicsExpanded.Project)
    subProject(Armory.Project)
    subProject(MinecoloniesWiki.Project)
    subProject(Animatrix.Project)
    subProject(BlockUI.Project)
    subProject(JVoxelizer.Project)
    subProject(Serverpacklocator.Project)
    subProject(SmithsCore.Project)
    subProject(Structurize.Project)
    subProject(PerViamInvenire.Project)
    subProject(PistonUnlimited.Project)
    subProject(DataGenerators.Project)
})
