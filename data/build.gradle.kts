import java.io.FileInputStream
import java.util.Properties
import kotlin.apply

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    implementation(libs.retrofit2)
    implementation(libs.converter.gson)

    implementation(libs.koin.android)
    implementation(libs.koin.compat)
    implementation(libs.koin.navigation)

    implementation(libs.pagingCompose)
    implementation(libs.pagingRuntime)
}