import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.freeDiskSpace
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.notifications

version = "2021.1"

        project {

            buildType(Anothertodolist_Build)
        }

object Anothertodolist_Build : BuildType({
    id("Build")
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = bundled_3_6()
        }
    }

    triggers {
            vcs {
            }
    }
    features {
        freeDiskSpace {
            requiredSpace = "6gb"
            failBuild = true
        }
        notifications {
            notifierSettings = emailNotifier {
                email = "tauputa@gmail.com"
            }
            buildStarted = true
            buildFailed = true
            buildFinishedSuccessfully = true
        }
    }
})
