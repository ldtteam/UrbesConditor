package _Self.buildTypes

import _Self.vcsRoots.General
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dockerCommand
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object Minecolonies_PullRequests_2_DeployTestServer_2 : Template({
    name = "Deploy test server"

    artifactRules = """
        +:build\libs\*-universal.jar => build\libs\
        +:build\libs\*-dev-server.jar => build\libs\
        +:build\changelog.md => build\
        +:build\distributions\mods-*.zip => build\distributions\
    """.trimIndent()
    buildNumberPattern = "%env.Version%"

    params {
        param("env.debug_port_number", "5005")
        password("env.private_ldtteam_webhook", "credentialsJSON:f66a8a9f-2931-44ac-a59c-d7f544a39f0b", display = ParameterDisplay.HIDDEN)
        param("env.node_number", "1")
        param("env.Version.Patch", "%dep.LetSDevTogether_Minecolonies_PullRequests_2_CommonBuildCounterOld.build.number%")
        param("env.forge_file_version", "<Unknown>")
        param("env.Version.Suffix", "-PR")
        password("env.public_testers_webhook", "credentialsJSON:56fcb36b-d734-433e-8674-7b1529cb3150", display = ParameterDisplay.HIDDEN)
        param("env.mc_version", "<Unknown>")
        param("env.port_number", "25565")
        param("env.forge_version", "<Unknown>")
    }

    vcs {
        root(_Self.vcsRoots.General)

        showDependenciesChanges = true
    }

    steps {
        script {
            name = "Determine version numbers"
            id = "RUNNER_103"
            scriptContent = """
                forge_version=${'$'}(cat build.properties | grep forge_version= | cut -d "=" -f2); echo ${'$'}forge_version
                mc_version=${'$'}(cat build.properties | grep minecraft_version= | cut -d "=" -f2); echo ${'$'}mc_version
                forge_file_version=${'$'}(echo "${'$'}{mc_version}-${'$'}{forge_version}"); echo ${'$'}forge_file_version
                
                echo "##teamcity[setParameter name='env.forge_version' value='${'$'}forge_version']"
                echo "##teamcity[setParameter name='env.mc_version' value='${'$'}mc_version']"
                echo "##teamcity[setParameter name='env.forge_file_version' value='${'$'}forge_file_version']"
            """.trimIndent()
            formatStderrAsError = true
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
        dockerCommand {
            name = "Docker build"
            id = "RUNNER_91"
            commandType = build {
                source = content {
                    content = """
                        # Default JDK is the source
                        FROM openjdk:8u222-stretch
                        
                        # Defining variables.
                        ARG min_ram=512M
                        ARG max_ram=3G
                        ARG forge_version
                        ARG mc_version
                        ARG forge_file_version
                        
                        # Create a working directory
                        WORKDIR /home/server
                        RUN apt-get install unzip -y
                        RUN echo Downloading https://files.minecraftforge.net/maven/net/minecraftforge/forge/${'$'}forge_file_version/forge-${'$'}forge_file_version-installer.jar
                        RUN curl https://files.minecraftforge.net/maven/net/minecraftforge/forge/${'$'}forge_file_version/forge-${'$'}forge_file_version-installer.jar > installer.jar
                        RUN java -jar installer.jar --installServer
                        RUN rm -rf mods
                        RUN ls
                        RUN echo eula=true >> eula.txt
                        RUN echo java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -server -ms${'$'}min_ram -Xmx${'$'}max_ram -jar forge-${'$'}forge_file_version-universal.jar nogui >> start.sh
                        RUN if [ -f "forge-${'$'}forge_file_version.jar" ]; then mv "forge-${'$'}forge_file_version.jar" "forge-${'$'}forge_file_version-universal.jar" ; fi;
                        RUN chmod +x start.sh
                        
                        # Vanilla data volumes
                        VOLUME /home/server/world
                        
                        # Copy mod files
                        RUN mkdir /home/server/mods
                        COPY build/ /home/server/input
                        RUN if [ "${'$'}mc_version" = "1.12.2" ] ; then cp '/home/server/input/libs/*-universal.jar' '/home/server/mods/' ; fi
                        RUN if [ "${'$'}mc_version" != "1.12.2" ] ; then unzip '/home/server/input/distributions/mods-*.zip' -d '/home/server/mods/' ; fi
                        RUN ls /home/server/mods
                        
                        # Copy server setup file
                        COPY scripts/server.properties /home/server/server.properties
                        COPY scripts/users.json /home/server/ops.json
                        COPY scripts/users.json /home/server/whitelist.json
                        
                        # Expose ports
                        EXPOSE 25565
                        EXPOSE 5005
                        
                        # Setting default run command
                        RUN mkdir /home/server/console_out/
                        CMD /home/server/start.sh | tee -i /home/server/console_out/running.log
                        
                        # Setup Healthcheck
                        HEALTHCHECK CMD cat /home/server/console_out/running.log | grep -e "DedicatedServer\]\: Done"
                    """.trimIndent()
                }
                namesAndTags = """
                    docker.minecolonies.com/minecolonies/minecraft/test-server:%build.number%
                    docker.minecolonies.com/minecolonies/minecraft/test-server:%teamcity.build.branch%
                """.trimIndent()
                commandArgs = "--pull --build-arg forge_version=%env.forge_version% --build-arg mc_version=%env.mc_version% --build-arg forge_file_version=%env.forge_file_version%"
            }
            param("dockerImage.platform", "linux")
        }
        dockerCommand {
            name = "Docker push"
            id = "RUNNER_104"
            commandType = push {
                namesAndTags = """
                    docker.minecolonies.com/minecolonies/minecraft/test-server:%build.number%
                    docker.minecolonies.com/minecolonies/minecraft/test-server:%teamcity.build.branch%
                """.trimIndent()
            }
        }
        script {
            name = "Determine service location"
            id = "RUNNER_92"
            scriptContent = """
                #!/bin/bash
                teamcity_build_branch=%teamcity.build.branch%
                stackprefix=ldtteam-testserver-
                
                port_number=0
                debug_port_number=0
                node_number=0
                
                possiblePorts=()
                get_available_ports() 
                {
                    for stack in ${'$'}stacks
                    do
                        server=${'$'}(docker stack services "${'$'}stack" --format '{{.Name}} {{.ID}}' | grep minecraft-server)
                        id=${'$'}{server: -12}
                        port=${'$'}(docker inspect "${'$'}id" | jq -r ".[].Spec.Labels.\"${'$'}port_label\"")
                
                        possiblePorts=( "${'$'}{possiblePorts[@]/${'$'}port}" )
                    done
                    echo "Ports available: ${'$'}{possiblePorts[*]}"
                    if [ "${'$'}{#possiblePorts[@]}" -eq 0 ]
                    then
                        echo "Failed to determine port to use. No port available"
                        exit 2
                    fi
                }
                
                if [[ ${'$'}(docker stack ls) == *"${'$'}stackprefix${'$'}teamcity_build_branch"* ]]
                then
                    echo "Stack ${'$'}stackprefix${'$'}teamcity_build_branch exists, using ports and node from it."
                    server=${'$'}(docker stack services "${'$'}stackprefix${'$'}teamcity_build_branch" --format '{{.Name}} {{.ID}}' | grep minecraft-server)
                    id=${'$'}{server: -12}
                    port_number=${'$'}(docker inspect "${'$'}id" | jq -r ".[].Spec.Labels.\"port\"")
                    debug_port_number=${'$'}(docker inspect "${'$'}id" | jq -r ".[].Spec.Labels.\"port.debug\"")
                    node_number=${'$'}(docker inspect "${'$'}id" | jq -r ".[].Spec.Labels.\"node.minecraft\"")
                    echo "Port in use: ${'$'}port_number"
                    echo "Node in use: ${'$'}node_number"
                else
                    echo "Stack ${'$'}stackprefix${'$'}teamcity_build_branch does not exist, finding an available port and node"
                    stacks=${'$'}(docker stack ls --format '{{.Name}}' | grep ${'$'}stackprefix)
                
                    mapfile -t possiblePorts < <(seq 25565 1 25585)
                    port_label=port
                    get_available_ports "${'$'}stacks" "${'$'}port_label"
                    for port in ${'$'}{possiblePorts[*]}
                    do
                        port_number=${'$'}port
                        break
                    done
                    echo "Found available Port: ${'$'}port_number"
                
                    mapfile -t possiblePorts < <(seq 5005 1 5025)
                    port_label=port.debug
                    get_available_ports "${'$'}stacks" "${'$'}port_label"
                    for port in ${'$'}{possiblePorts[*]}
                    do
                        debug_port_number=${'$'}port
                        break
                    done
                    echo "Found available Debug Port: ${'$'}debug_port_number"
                
                    node_number=1
                    echo "Found available Node: ${'$'}node_number"
                fi
                
                if [[ ${'$'}port_number == 0 ]]
                then
                    echo "Could not find available port"
                    exit 1
                fi
                
                if [[ ${'$'}debug_port_number == 0 ]]
                then
                    echo "Could not find available debug port"
                    exit 1
                fi
                
                if [[ ${'$'}node_number == 0 ]]
                then
                    echo "Could not find available node"
                    exit 1
                fi
                
                echo "##teamcity[setParameter name='env.port_number' value='${'$'}port_number']"
                echo "##teamcity[setParameter name='env.debug_port_number' value='${'$'}debug_port_number']"
                echo "##teamcity[setParameter name='env.node_number' value='${'$'}node_number']"
            """.trimIndent()
            formatStderrAsError = true
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
        script {
            name = "Docker deploy stack"
            id = "RUNNER_107"
            scriptContent = """
                echo "version: '3.1'
                
                services:
                  minecraft-server:
                    image: docker.minecolonies.com/minecolonies/minecraft/test-server:%build.number%
                    ports:
                      - \"%env.port_number%:25565\"
                      - \"%env.debug_port_number%:5005\"
                    deploy:
                      restart_policy:
                        condition: any
                        max_attempts: 2
                        window: 10m
                      replicas: 1
                      placement:
                        constraints:
                          - node.labels.node.minecraft == %env.node_number%
                      labels:
                        - node.minecraft=%env.node_number%
                        - port=%env.port_number%
                        - port.debug=%env.debug_port_number%
                        - pr=%teamcity.build.branch%
                    volumes:
                      - minecraft-save:/home/server/world
                
                volumes:
                  minecraft-save:" | 
                docker stack deploy -c - ldtteam-testserver-%teamcity.build.branch%
            """.trimIndent()
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
        script {
            name = "Determine Server Status"
            id = "RUNNER_110"
            scriptContent = """
                #!/bin/bash
                targetTime=${'$'}((${'$'}(date +%s) + 360))
                link="https://teamcity.minecolonies.com/viewLog.html?buildId=%teamcity.build.id%&buildTypeId=%system.teamcity.buildType.id%"
                while [[ ! ${'$'}(docker stack ls) == *"ldtteam-testserver-%teamcity.build.branch%"* ]]
                do
                  if [[ ${'$'}(date +%s) > ${'$'}targetTime ]]
                  then
                    echo "Stack took too long to exist, exiting."
                    curl -u "LDTTeam-Buildsystem:%env.GITHUB_TOKEN%" -H "Content-Type: application/json" -X POST -d "{\"body\": \"This PR has been automatically built and hosted by our build system, during this process there was a crash, error, or otherwise, please check on this at: ${'$'}link\"}" https://api.github.com/repos/ldtteam/minecolonies/issues/%teamcity.build.branch%/comments
                    exit 1
                  fi
                  # Here we're waiting for the stack to exist
                  sleep 10
                done
                
                targetTime=${'$'}((${'$'}(date +%s) + 360))
                while [[ ! ${'$'}(docker stack ps --format "{{.Name}} {{.DesiredState}}" ldtteam-testserver-%teamcity.build.branch%) == *"Running"* ]]
                do
                  if [[ ${'$'}(date +%s) > ${'$'}targetTime ]]
                  then
                    echo "Service took too long to exist, exiting."
                    curl -u "LDTTeam-Buildsystem:%env.GITHUB_TOKEN%" -H "Content-Type: application/json" -X POST -d "{\"body\": \"This PR has been automatically built and hosted by our build system, during this process there was a crash, error, or otherwise, please check on this at: ${'$'}link\"}" https://api.github.com/repos/ldtteam/minecolonies/issues/%teamcity.build.branch%/comments
                    exit 1
                  fi
                  # Here we're waiting until the stack is properly ready to go.
                  sleep 10
                done
                
                server=${'$'}(docker stack ps --format "{{.Name}} {{.ID}}" --filter "desired-state=Running" ldtteam-testserver-%teamcity.build.branch%)
                id=${'$'}{server: -12}
                targetTime=${'$'}((${'$'}(date +%s) + 360))
                while [[ ${'$'}(date +%s) < ${'$'}targetTime ]]
                do
                  state=${'$'}(docker inspect "${'$'}id" | jq -r ".[].Status.State")
                  if [[ ${'$'}state == "running" ]]
                  then
                    echo "Container Running!"
                    exit 0
                  elif [[ ${'$'}state == "failed" ]]
                  then
                    echo "Container Failed!"
                    docker service logs "${'$'}id"
                    curl -u "LDTTeam-Buildsystem:%env.GITHUB_TOKEN%" -H "Content-Type: application/json" -X POST -d "{\"body\": \"This PR has been automatically built and hosted by our build system, during this process there was a crash, error, or otherwise, please check on this at: ${'$'}link\"}" https://api.github.com/repos/ldtteam/minecolonies/issues/%teamcity.build.branch%/comments
                    exit 1
                  elif [[ ${'$'}state == "starting" ]]
                  then
                    echo "Container is Starting!"
                  elif [[ ${'$'}state == "new" || ${'$'}state == "pending" || ${'$'}state == "preparing" || ${'$'}state == "assigned" || ${'$'}state == "accepted" ]]
                  then
                    echo "Docker Swarm is processing"
                  else
                    echo "Container state is: ${'$'}state, exiting"
                    docker service logs "${'$'}id"
                    curl -u "LDTTeam-Buildsystem:%env.GITHUB_TOKEN%" -H "Content-Type: application/json" -X POST -d "{\"body\": \"This PR has been automatically built and hosted by our build system, during this process there was a crash, error, or otherwise, please check on this at: ${'$'}link\"}" https://api.github.com/repos/ldtteam/minecolonies/issues/%teamcity.build.branch%/comments
                    exit 1
                  fi
                  sleep 5
                done
                echo "Container took too long to start up!"
                docker service logs "${'$'}id"
                curl -u "LDTTeam-Buildsystem:%env.GITHUB_TOKEN%" -H "Content-Type: application/json" -X POST -d "{\"body\": \"This PR has been automatically built and hosted by our build system, during this process there was a crash, error, or otherwise, please check on this at: ${'$'}link\"}" https://api.github.com/repos/ldtteam/minecolonies/issues/%teamcity.build.branch%/comments
                exit 1
            """.trimIndent()
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
        gradle {
            name = "Generate changelog"
            id = "RUNNER_108"
            tasks = "createChangelog"
            buildFile = "build.gradle"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
        }
        script {
            name = "Notify testers of new build"
            id = "RUNNER_109"
            scriptContent = """
                post=${'$'}(echo "{
                  \"avatar_url\": \"https://avatars3.githubusercontent.com/u/40539185?s=460&v=4\",
                  \"username\": \"LDTTeam-Buildsystem\",
                  \"embeds\": [
                  {
                    \"title\": \"A New PR Has been built!\",
                    \"description\": \"A new version has been built for testing!\",
                    \"color\": 2969574,
                    \"timestamp\": \"${'$'}(date -u +"%%Y-%%m-%%dT%%H:%%M:%%SZ")\",
                    \"fields\": [
                    {
                      \"name\": \"Pull Request Number\",
                      \"value\": \"%teamcity.build.branch%\",
                      \"inline\": true
                    },
                    {
                      \"name\": \"Minecraft Version\",
                      \"value\": \"%env.mc_version%\",
                      \"inline\": true
                    },
                    {
                      \"name\": \"Forge Version\",
                      \"value\": \"%env.forge_version%\",
                      \"inline\": true
                    },
                    {
                      \"name\": \"Download Jar here:\",
                      \"value\": \"https://teamcity.minecolonies.com/guestAuth/repository/download/%system.teamcity.buildType.id%/%teamcity.build.id%:id/build/libs/%filename.prefix%-%env.mc_version%-%dep.LetSDevTogether_Minecolonies_PullRequests_2_BuildAndTestOld.build.number%-universal.jar\"
                    },
                    {
                      \"name\": \"Read Changelog here:\",
                      \"value\": \"https://teamcity.minecolonies.com/guestAuth/repository/download/%system.teamcity.buildType.id%/%teamcity.build.id%:id/build/changelog.md\"
                    },
                    {
                      \"name\": \"Server IP:\",
                      \"value\": \"You can access the server at: minecolonies.testing.ldtteam.com:%env.port_number%\"
                    }]
                  }]
                }")
                
                if [ "${'$'}mc_version" != "1.12.2" ]; then
                post=${'$'}(echo "{
                  \"avatar_url\": \"https://avatars3.githubusercontent.com/u/40539185?s=460&v=4\",
                  \"username\": \"LDTTeam-Buildsystem\",
                  \"embeds\": [
                  {
                    \"title\": \"A New PR Has been built!\",
                    \"description\": \"A new version has been built for testing!\",
                    \"color\": 2969574,
                    \"timestamp\": \"${'$'}(date -u +"%%Y-%%m-%%dT%%H:%%M:%%SZ")\",
                    \"fields\": [
                    {
                      \"name\": \"Pull Request Number\",
                      \"value\": \"%teamcity.build.branch%\",
                      \"inline\": true
                    },
                    {
                      \"name\": \"Minecraft Version\",
                      \"value\": \"%env.mc_version%\",
                      \"inline\": true
                    },
                    {
                      \"name\": \"Forge Version\",
                      \"value\": \"%env.forge_version%\",
                      \"inline\": true
                    },
                    {
                      \"name\": \"Download Mods.zip here:\",
                      \"value\": \"https://teamcity.minecolonies.com/guestAuth/repository/download/LetSDevTogether_Minecolonies_PullRequests_2_BuildAndTest/%teamcity.build.id%:id/build/distributions/mods-%dep.LetSDevTogether_Minecolonies_PullRequests_2_BuildAndTestOld.build.number%.zip\"
                    },
                    {
                      \"name\": \"Read Changelog here:\",
                      \"value\": \"https://teamcity.minecolonies.com/guestAuth/repository/download/%system.teamcity.buildType.id%/%teamcity.build.id%:id/build/changelog.md\"
                    },
                    {
                      \"name\": \"Server IP:\",
                      \"value\": \"You can access the server at: minecolonies.testing.ldtteam.com:%env.port_number%\"
                    }]
                  }]
                }")
                fi;
                
                curl -H "Content-Type: application/json" -X POST -d "${'$'}post" %env.public_testers_webhook%
                #curl -H "Content-Type: application/json" -X POST -d "{\"username\": \"MineColonies Testing\", \"content\": \"A new version for PR %teamcity.build.branch% has been released for testing!\n\nDownload jar here:\nhttps://teamcity.minecolonies.com/guestAuth/repository/download/%system.teamcity.buildType.id%/%teamcity.build.id%:id/build/libs/%filename.prefix%-%env.mc_version%-%dep.LetSDevTogether_Minecolonies_PullRequests_2_BuildAndTestOld.build.number%-universal.jar\n\nDownload changelog here:\nhttps://teamcity.minecolonies.com/guestAuth/repository/download/%system.teamcity.buildType.id%/%teamcity.build.id%:id/build/changelog.md\n\nJoin other testers here: minecolonies.testing.ldtteam.com:%env.port_number%\"}" %env.public_testers_webhook%
                #Below is only for LDTTeam members to see:
                curl -H "Content-Type: application/json" -X POST -d "{\"username\": \"MineColonies Testing\", \"content\": \"PR <https://github.com/ldtteam/minecolonies/pull/%teamcity.build.branch%> was built, you may use remote debugging here: minecolonies.testing.ldtteam.com:%env.debug_port_number%\"}" %env.private_ldtteam_webhook%
            """.trimIndent()
            formatStderrAsError = true
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
    }

    triggers {
        vcs {
            id = "vcsTrigger"
        }
    }

    features {
        commitStatusPublisher {
            id = "BUILD_EXT_16"
            vcsRootExtId = "${General.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:ef1005cd-d382-4bf1-b9a0-91dbf6565066"
                }
            }
        }
    }
})
