// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    // Dependency injection with Hilt
    // https://developer.android.com/training/dependency-injection/hilt-android
    // https://mvnrepository.com/artifact/com.google.dagger/hilt-android-gradle-plugin
    id("com.google.dagger.hilt.android") version "2.44" apply false
}