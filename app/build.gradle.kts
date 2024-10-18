plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ktlintGradle)
    id("kotlin-kapt")
}

android {
    namespace = "com.cooktogether"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.cooktogether"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
    buildFeatures {
        compose = true
    }
    buildToolsVersion = "35.0.0"
    ndkVersion = "25.1.8937393"
}

dependencies {
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.espresso.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.coil.compose)
    // Navigation
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.tab.navigator)
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // Strings
    implementation(libs.lyricist)
    // Request
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // ViewModel para MVVM
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // UI
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
}