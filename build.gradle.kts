allprojects {
    repositories {
        flatDir {
            dirs("libs")
        }
        google()
        jcenter()
        gradlePluginPortal()
    }
}
