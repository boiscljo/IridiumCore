rootProject.name = "IridiumCore"
pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}
include("plugin")
include("multiversion")
include("multiversion:common")
include("multiversion:default")