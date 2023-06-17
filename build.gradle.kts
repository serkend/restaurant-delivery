// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    id 'com.android.application' version '8.0.2' apply false
//    id 'com.android.library' version '8.0.2' apply false
//    id 'org.jetbrains.kotlin.android' version '1.7.20' apply false
//}

buildscript {

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Project.Android.androidGradle)
        classpath(Project.Android.kotlinGradle)
        classpath(Project.Dagger.daggerHilt)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.withType<Test> {
    useJUnitPlatform()
}