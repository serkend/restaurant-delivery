@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlin)
    id(Plugins.kapt)
    id(Plugins.daggerHilt)
}

android {
    namespace = "com.example.domain"
    compileSdk = Android.compileSdk

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget =  Config.jvmTarget
    }
}

dependencies {
    implementation(project(":common"))
    implementation(Libs.Application.DependencyInjection.hilt)
    implementation(Libs.Application.DataStore.datastore)
    kapt(Libs.Application.DependencyInjection.hilt_compiler)

    implementation(Libs.Application.Network.Retrofit.retrofit)
    implementation(Libs.Application.Network.Retrofit.retrofit_gson)
    implementation(Libs.Application.Network.OkHttp.okhttp)
    implementation(Libs.Application.Network.OkHttp.okhttp_logging)

    implementation(Libs.View.material)
    implementation(Libs.View.lifecycleRuntime)
    implementation(Libs.View.lifecycleViewModel)
    implementation(Libs.View.fragmentKtx)

    implementation(Libs.Application.DependencyInjection.hilt)
    implementation(Libs.View.coreKtx)
    implementation(Libs.View.lifecycleRuntime)
    implementation(Libs.Application.Database.roomRuntime)
    implementation(Libs.Application.Database.roomKtx)

    //Compose
    implementation(Libs.Compose.material3)
    implementation(Libs.Boom.activityCompose)
    implementation(Libs.Boom.viewModelCompose)
    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.navigation)
    implementation(Libs.Compose.systemUiController)
    implementation(Libs.Compose.preview)
    implementation(Libs.Compose.tooling)
    implementation(Libs.Compose.material)
    implementation(Libs.Compose.systemUiController)
    implementation(Libs.Application.DependencyInjection.hiltNavigationCompose)

    kapt(Libs.Application.Database.kaptRoom)
    testImplementation(Libs.View.Test.jUnit)
    androidTestImplementation(Libs.View.AndroidTest.jUnit)
    androidTestImplementation(Libs.View.AndroidTest.espresso)
}