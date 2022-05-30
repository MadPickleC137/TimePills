package com.madpickle.buildsrc

object Libs {

    const val material = "com.google.android.material:material:1.4.0"
    const val neumorphism = "com.github.fornewid:neumorphism:0.3.0"

    object Coroutines {
        private const val version = "1.5.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.1"
        const val coreKtx = "androidx.core:core-ktx:1.6.0"

        object Navigation {
            private const val version = "2.3.5"
            const val navFragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val navUi =  "androidx.navigation:navigation-ui-ktx:$version"
            const val navDynamic = "androidx.navigation:navigation-dynamic-features-fragment:$version"
        }

        object Test {
            const val junit = "junit:junit:4.13.2"
            const val junitExt = "androidx.test.ext:junit-ktx:1.1.3"
            const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"
        }

        object Lifecycle {
            private const val version = "2.3.1"
            const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val service = "androidx.lifecycle:lifecycle-service:$version"
        }
    }
    object DI {
        private const val version = "2.38.1"
        const val hiltKapt = "com.google.dagger:hilt-compiler:$version"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltFragment = "androidx.hilt:hilt-navigation-fragment:1.0.0"
    }

    object Coil {
        const val coilKt = "io.coil-kt:coil:1.3.2"
    }

    object Truth{
        const val truthTest = "com.google.truth:truth:1.1.3"
    }

    object Logs {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
    }
}