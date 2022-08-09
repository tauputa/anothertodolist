import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

version = "2021.1"

project {
    buildType(Build)
    buildType(IntegrationTest)
    buildType(UnitTest)
    buildType(Package)

    sequential {
        buildType(Build)
        parallel{
            buildType(IntegrationTest)
            buildType(UnitTest)
        }
        buildType(Package)
    }
}

object Build : BuildType({
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
})

object IntegrationTest : BuildType({
    id("IntegrationTest")
    name = "Integration Test"
    vcs {
        root(DslContext.settingsRoot)
    }
    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true -Dtest=*.integration.*Test"
            mavenVersion = bundled_3_6()
        }
    }
})

object UnitTest : BuildType({
    id("UnitTest")
    name = "Unit Test"
    vcs {
        root(DslContext.settingsRoot)
    }
    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true -Dtest=*.unit.*Test"
            mavenVersion = bundled_3_6()
        }
    }
})

object Package : BuildType({
    id("Package")
    name = "Package"
    vcs {
        root(DslContext.settingsRoot)
    }
    steps {
        maven {
            goals = "clean package"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            mavenVersion = bundled_3_6()
        }
    }
    triggers {
        vcs {
        }
    }
})

