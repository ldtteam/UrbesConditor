package Minecolonies_PullRequests_2.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Minecolonies_PullRequests_2_BuildAndTest : BuildType({
    templates(_Self.buildTypes.BuildWithTesting)
    name = "Build and Test"
    description = "Builds and Tests the pull request."

    artifactRules = """
        +:build\libs\*.jar => build\libs
        +:build\distributions\mods-*.zip => build\distributions
    """.trimIndent()

    params {
        param("env.Version.Patch", "${Minecolonies_PullRequests_2_CommonBuildCounter.depParamRefs.buildNumber}")
        param("env.Version.Suffix", "-PR")
    }

    features {
        feature {
            id = "com.ldtteam.teamcity.github.commenting.GithubCommentingBuildFeature"
            type = "com.ldtteam.teamcity.github.commenting.GithubCommentingBuildFeature"
            param("privateKey", "-----BEGIN PRIVATE KEY----- MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCloBWRvqdhaqvG 0rKDF8REF70Sbb3lRQ77Ta9ufcGSlok64NByQSM4bszwtmHq6jZMtELyMROHip/s u9QH/1OLyKzjFVh5hlAu1I8J/Yois5KSI1yKcnRpINbTCeeoRceTIQ9EbUee9N5B pKqXlA68Y04LP2S/eMbpLDFyWS6kNcziO/+P5WGHNkqlN7e/TuJIXyk0aXhfrf2U NvDPRR7WSpBnYcyrorMd7VoFOKqu5GsIR74BiirIRDtZI8ZkuRrezJO0pXO8YmmP 2LnQldK2Bt6pL86WUohY+eCTjIPA6vwvVelY5Y9nxXQU9kSNyh9GUxRaReLUptDb r9LiOM7zAgMBAAECggEBAIofLJ20v2WwTbyrbY+BvH2Exnqd5mVS6CGPMaQVX97d h2gAoBUIWVTwohyEHLiJxerF0aakYsMASkpkgXiB56yFGBjwbi46YpgLT5ZnSFGU K8GPl9cliAs8BatNcVGVj9AVG+RWtgnL76YSqulp4bgdoLIkleJP2KIP5tSTpx/t HnOQ2beW1VK5hW9zxHrUQ/mVJgs1Ec3T6EhYuC0nAz9nKXiPK5FN4YwGK8iv2NAE WGpu3GdSusXEoRZhIJw2kHmDG1MdUJogqtKM9jmpfnUUgBjffB5wgM4EvTzfAzC1 Xe+gws5c1oHm+vBmPpWzYBpKyJtwST2YCBvMNvrmdfECgYEA054+qbfWSmLF3JLK P1hOyzzI9dl6airJZ9+sUofN5+bHqaBDfz00fim7COgQZ6RaHo3JEOjaZB6HcvKc LZuQoXE5tlr8pFGb3anY0LzE9vEoP0gATtvGwmgG7X8Tm1sNSochzepH1S4Lps0E WoHkEIo1cUdq1zMiZ6rBrfOAOY8CgYEAyFx9gKwqy/dM0kSPRT2jFJw7rdv0UzBE NostTQpOPxCpsLwnn4CdzUJ6Ig3mA3hr9oq+DAMsHguu1G8GhQDropmiKwdi7RQw uoRzkH/zyCsd+30t3UACYqNAtSZYZoVb0nDCo72vFRK4QJEc8kMyw4xyddM257SC drgn+pMOul0CgYEAz0JOhzT5WTNyLH1MPkxEJ8Op4zgUMAUl62ljQhWmMjmSVe0U DnuofORsPeXjo06RwRkG01vsAWDxMkCxe+2/8o6Ngq+Mf3c6XBhstMwdcOpyi7/+ JT4VZfQyzMrILkE4PEUrc5zAq+cEHGLxn59V+mkSvJbA6nI3gSb5uGAjtrsCgYBp M78mD8BM5mWsxhjgB1QYn0vqpuIrx90ZMoyvteiNzob66GB5rIOu4efU9609Rz6Q 7hqJniTNBd9fZYhrz6bp8ncm1rMHvqEzXhZ72eM7x//vK7QoRtK5zfRM6XxjIESt Q02/wforO3AJXuhu//+ok5cJ7MQYdzxFb752ID8MdQKBgDBZijxu+1tvVj22Toid W968PNYo5JB0gembxIiz3rMJWupWXzcab2Gi3rVkwTLKQIZCS5Em8Yh/wTUjXnZH qXatbTrFIBJ/0/plGpHaM9VjwlbgcmiOCH5v5FSc6bqL7GcO1On0jgd/hubGjEQx htFQ3n5LrY260xV+5LctKFZi -----END PRIVATE KEY-----")
            param("appId", "154983")
            param("branch", "%teamcity.build.branch%")
        }
    }

    dependencies {
        snapshot(Minecolonies_PullRequests_2_CommonBuildCounter) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
    
    disableSettings("BUILD_EXT_15")
})
