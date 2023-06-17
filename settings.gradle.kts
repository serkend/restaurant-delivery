pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
rootProject.name = "RestaurantTestTask"
include (":data")
include (":domain")
include(":app")
include(":common")
