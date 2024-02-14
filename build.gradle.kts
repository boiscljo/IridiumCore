plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}
defaultTasks("shadowJar","publishToMavenLocal")
group = "com.iridium"
version = "1.6.12-OM-folia"
description = "IridiumCore"

allprojects {
    apply(plugin = "java")

    java.sourceCompatibility = JavaVersion.VERSION_17

    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://moyskleytech.com/debian/m2")
        maven("https://repo.codemc.org/repository/maven-public/")
        maven("https://repo.rosewooddev.io/repository/public/")
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://nexus.iridiumdevelopment.net/repository/maven-releases/")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    dependencies {
        // Dependencies that we want to shade in
        implementation("org.jetbrains:annotations:23.0.0")
        implementation("com.moyskleytech:ObsidianMaterialAPI:1.0.6")
        implementation("com.github.cryptomorin:XSeries:9.3.1")
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
    assemble {
        dependsOn("shadowJar")
    }

    shadowJar {
        archiveClassifier.set("")
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
        setGroupId("com.iridium")
        setArtifactId("IridiumCore")
        setVersion(version)
        artifact(tasks["shadowJar"])
    }
}
