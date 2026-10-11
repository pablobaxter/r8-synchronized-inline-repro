pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    // -Pr8.version=<version> swaps the R8 bundled with AGP for a release from Google's R8 bucket
    buildscript {
        val r8Version = providers.gradleProperty("r8.version")
        repositories {
            if (r8Version.isPresent) {
                maven {
                    url = uri("https://storage.googleapis.com/r8-releases/raw")
                    content { includeModule("com.android.tools", "r8") }
                }
            }
        }
        dependencies {
            if (r8Version.isPresent) {
                classpath(r8Version.map { "com.android.tools:r8:$it" })
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "r8-synchronized-inline-repro"
include(":app")
