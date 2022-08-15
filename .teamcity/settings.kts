import jetbrains.buildServer.configs.kotlin.v2019_2.*

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
})