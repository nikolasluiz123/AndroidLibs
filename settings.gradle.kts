pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidLibs"

include(":core-utils")
include(":core-android-utils")

include(":samples")
include(":ui-compose-components")
include(":firebase-toolkit")
include(":android-pdf-generator")
include(":compose-charts")
include(":room-toolkit")
include(":health-connect-toolkit")
include(":work-manager-toolkit")
include(":core-android-compose-utils")
