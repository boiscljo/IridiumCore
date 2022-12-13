plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "com.iridium"
version = "1.6.7-OM.2"
description = "IridiumCore"

allprojects {
    apply(plugin = "java")

    java.sourceCompatibility = JavaVersion.VERSION_1_8

    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://moyskleytech.com/debian/m2")
        maven("https://repo.codemc.org/repository/maven-public/")
        maven("https://repo.rosewooddev.io/repository/public/")
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://nexus.iridiumdevelopment.net/repository/maven-releases/")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    dependencies {
        // Dependencies that we want to shade in
        implementation("org.jetbrains:annotations:23.0.0")
        implementation("com.moyskleytech:ObsidianMaterialAPI:1.0.3-SNAPSHOT")
        implementation("com.github.cryptomorin:XSeries:9.1.0")
        // Other dependencies that are not required or already available at runtime
        compileOnly("org.projectlombok:lombok:1.18.22")

        // Enable lombok annotation processing
        annotationProcessor("org.projectlombok:lombok:1.18.22")
    }
}

dependencies {
    // Shade all the sub-projects into the jar
    subprojects.forEach { implementation(it) }
}

tasks {
    jar {
        dependsOn("shadowJar")
        enabled = false
    }

    shadowJar {
        archiveClassifier.set("")
        relocate("de.tr7zw.changeme.nbtapi", "com.iridium.iridiumcore.dependencies.nbtapi")
        relocate("com.iridium.iridiumcolorapi", "com.iridium.iridiumcore.dependencies.iridiumcolorapi")
        relocate("org.yaml.snakeyaml", "com.iridium.iridiumcore.dependencies.snakeyaml")
        relocate("io.papermc.lib", "com.iridium.iridiumcore.dependencies.paperlib")
        relocate("com.fasterxml.jackson", "com.iridium.iridiumcore.dependencies.fasterxml")
        relocate("com.cryptomorin.xseries", "com.moyskleytech.obsidian.material.dependencies.xseries")

    }

    compileJava {
        options.encoding = "UTF-8"
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
