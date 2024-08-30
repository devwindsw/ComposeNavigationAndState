// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    // Dependency injection with Hilt
    // https://developer.android.com/training/dependency-injection/hilt-android
    // https://mvnrepository.com/artifact/com.google.dagger/hilt-android-gradle-plugin
    // Issue: A failure occurred while executing org.jetbrains.kotlin.gradle.internal.KaptWithoutKotlincTask$KaptExecutionWorkAction
    // Solution: Changing 2.44 -> 2.51.1
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}