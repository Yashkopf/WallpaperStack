import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.wallpaperstack"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.wallpaperstack"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val apiKey: String = project.findProperty("API_KEY") as String? ?: ""
            val prop = Properties().apply{
                load(FileInputStream(File(rootProject.rootDir,"local.properties")))
            }
            buildConfigField("String", "BASE_URL", "\"https://wallhaven.cc\"")
            buildConfigField ("String", "API_KEY", prop.getProperty("API_KEY"))

        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val prop = Properties().apply{
                load(FileInputStream(File(rootProject.rootDir,"local.properties")))
            }
            buildConfigField ("String", "BASE_URL", "\"https://wallhaven.cc\"")
            buildConfigField ("String", "API_KEY", prop.getProperty("API_KEY"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    kapt {
        generateStubs = true
    }
}

dependencies {

    implementation (libs.converter.gson)
    implementation(libs.retrofit2)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    kapt ("com.github.bumptech.glide:compiler:4.14.2")
    // transformation image for Glide
    implementation ("jp.wasabeef:glide-transformations:4.3.0")

    //pagination
    implementation("androidx.paging:paging-runtime:3.3.2")
    implementation("androidx.paging:paging-compose:3.3.2")

    // dagger android
    kapt ("com.google.dagger:dagger-android-processor:2.46.1")
    implementation ("com.google.dagger:dagger-android:2.46.1")
    implementation ("com.google.dagger:dagger-android-support:2.46.1")

    //Koin
    implementation("io.insert-koin:koin-android:4.0.2")
    // Java Compatibility
    implementation("io.insert-koin:koin-android-compat:4.0.2")
    // Navigation Graph
    implementation("io.insert-koin:koin-androidx-navigation:4.0.2")

    //Facebook Shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    //SwipeToRefresh
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation ("androidx.fragment:fragment-ktx:1.8.6")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //dagger-hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("androidx.recyclerview:recyclerview:1.4.0")
    implementation ("com.google.android.material:material:1.12.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}