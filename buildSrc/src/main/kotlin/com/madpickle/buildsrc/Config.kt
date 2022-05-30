package com.madpickle.buildsrc

object AppConfig {
    private const val version = "1.6.0"
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.3"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:2.38.1"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
    const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    const val realm = "io.realm:realm-gradle-plugin:10.8.0"

    const val minSdk = 21
    const val compileSdk = 31
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val applicationId = "com.madpickle.timepills"

}