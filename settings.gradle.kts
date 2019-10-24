rootProject.buildFileName = "build.gradle.kts"

include(":hyperion-strictmode")

pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
    }

    plugins {
        id("org.jetbrains.kotlin.android") version "1.3.50"
        id("org.jetbrains.kotlin.kapt") version "1.3.50"
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:3.6.0-beta01")
            }
        }
    }
}
