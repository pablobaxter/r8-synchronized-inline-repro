plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.r8repro"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.r8repro"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        create("repro") {
            storeFile = rootProject.file("repro.jks")
            storePassword = "android"
            keyAlias = "repro"
            keyPassword = "android"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("repro")
        }
    }
}
