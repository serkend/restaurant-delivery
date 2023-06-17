@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.application)
    id(Plugins.kotlin)
    id(Plugins.kapt)
    id(Plugins.daggerHilt)
}

android {
    namespace = Android.applicationId
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = Android.applicationId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = Android.testInstrumentalRunner

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isDebuggable = Obfuscation.releaseDebuggable
            isMinifyEnabled = Obfuscation.releaseMinifyEnabled
            isShrinkResources = Obfuscation.releaseMinifyEnabled

            signingConfig = signingConfigs.getByName("debug")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
        }
        debug {
            isDebuggable = Obfuscation.debugDebuggable
            isMinifyEnabled = Obfuscation.debugMinifyEnabled
            isShrinkResources = Obfuscation.debugMinifyEnabled

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.compatibleJavaVersion
        targetCompatibility = Config.compatibleJavaVersion
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    packagingOptions {
        resources {
            resources.excludes.add("META-INF/AL2.0")
            resources.excludes.add("META-INF/LGPL2.1")
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Compose.composeVersion
    }
}

dependencies {
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.google.accompanist:accompanist-permissions:0.25.0")

    implementation(Libs.Compose.coilCompose)
    implementation(project(":domain"))
    implementation(project(":data"))
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
    implementation(Libs.Compose.extended_icons)
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