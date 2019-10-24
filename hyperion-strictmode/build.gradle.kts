import java.util.Properties
import java.io.FileInputStream

plugins {
    id("maven-publish")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

group = "com.alapshin.hyperion"
version = "0.1.0"

android {
    compileSdkVersion(Versions.compileSdk)

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
    }

    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    dependencies {
        implementation(Libraries.hyperioncore)
        implementation(Libraries.kotlinstdlib)
        kapt(Libraries.autoservice)
    }
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                artifact(tasks["sourcesJar"])
                from(components.getByName("release"))
            }
        }
        repositories {
            maven {
                name = "Github"
                url = uri("https://maven.pkg.github.com/alapshin/hyperion-strictmode")
                val props = Properties().apply {
                    val file = rootProject.file("publishing.properties")
                    if (file.exists()) {
                        load(FileInputStream(file))
                    }
                }
                credentials {
                    username = props["gpr.user"] as String? ?: System.getenv("GPR_USER")
                    password = props["gpr.key"] as String? ?: System.getenv("GPR_API_KEY")
                }
            }
        }
    }
}
