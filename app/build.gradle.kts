plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.gms.google-services") version "4.4.2" apply false
//    id("com.android.application")
//    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.west"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.west"
        minSdk = 26
        targetSdk = 34
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
        compose = true // Enable Jetpack Compose for this project.
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
}


dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    // Jetpack Compose Bill of Materials
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    // Jetpack Compose dependencies
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    // ArcGIS Map Kotlin SDK dependencies
    implementation("com.esri:arcgis-maps-kotlin:200.4.0")
    // Toolkit dependencies
    implementation(platform("com.esri:arcgis-maps-kotlin-toolkit-bom:200.4.0"))
    implementation("com.esri:arcgis-maps-kotlin-toolkit-geoview-compose")
    implementation("com.google.android.material:material:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-guava:1.5.1")
    implementation("com.squareup.okhttp3:okhttp")
    implementation(libs.play.services.location)
    implementation(libs.androidx.navigation.compose)
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
}