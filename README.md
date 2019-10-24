# Hyperion StrictMode

[Hyperion-Android][1] plugin that allows to toggle [StrictMode][2] in runtime

## Usage
Add Jitpack maven repository
```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

Add plugin dependency in build.gradle
```
dependencies {
    debugImplementation "com.github.alapshin:hyperion-strictmode:$plugin_version"
    debugImplementation "com.willowtreeapps.hyperion:hyperion-core:$hyperion_version"
}
```

[1]: https://github.com/willowtreeapps/Hyperion-Android
[2]: https://developer.android.com/reference/android/os/StrictMode
