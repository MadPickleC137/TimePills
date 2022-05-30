import com.madpickle.buildsrc.AppConfig
import com.madpickle.buildsrc.Libs

plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id ("realm-android")
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    maven( url = "https://jitpack.io" )
}
android {

    compileSdk = AppConfig.compileSdk
    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.compileSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner  = AppConfig.testInstrumentRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }

    android {
        splits {
            abi {
                isEnable = true
                reset()
                include ("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
            }
        }
    }
}

dependencies {
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt (Libs.DI.hiltKapt)

    implementation( Libs.AndroidX.coreKtx)
    implementation( Libs.AndroidX.appCompat)
    implementation( Libs.AndroidX.constraintLayout)
    implementation( Libs.material)
    implementation( Libs.neumorphism)

    implementation( Libs.AndroidX.Navigation.navUi)
    implementation( Libs.AndroidX.Navigation.navDynamic)
    implementation( Libs.AndroidX.Navigation.navFragment)

    implementation( Libs.AndroidX.Lifecycle.service)
    implementation( Libs.AndroidX.Lifecycle.savedState)
    implementation( Libs.AndroidX.Lifecycle.liveData)
    implementation( Libs.AndroidX.Lifecycle.viewModel)

    implementation( Libs.Coroutines.core)
    implementation( Libs.Coroutines.android)
    testImplementation (Libs.Coroutines.test)

    implementation( Libs.DI.hiltAndroid)
    implementation (Libs.DI.hiltFragment)

    implementation( Libs.Coil.coilKt)

    implementation( Libs.Logs.timber)

    testImplementation (Libs.Truth.truthTest)
    testImplementation (Libs.AndroidX.Test.junit)
    testImplementation (Libs.AndroidX.Test.core)
    testImplementation (Libs.AndroidX.Test.rules)
    androidTestImplementation (Libs.AndroidX.Test.junitExt)
    androidTestImplementation (Libs.AndroidX.Test.espresso)
}

kapt {
    correctErrorTypes = true
}