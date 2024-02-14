// plugins{
//     id("io.papermc.paperweight.userdev") version "1.5.11"
// }
java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    // Dependencies that we want to shade in
    implementation("com.iridium:IridiumColorAPI:1.0.6")
    implementation("org.jetbrains:annotations:22.0.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.1")
    implementation("org.yaml:snakeyaml:1.29")
    implementation("io.papermc:paperlib:1.0.7")
    
    // Other dependencies that are not required or already available at runtime
    //compileOnly("org.spigotmc:spigot-api:1.19-R0.1-SNAPSHOT")
    compileOnly("dev.folia:folia-api:1.20.2-R0.1-SNAPSHOT")
    //paperweight.foliaDevBundle("1.20.2-R0.1-SNAPSHOT")
    //paperweight.paperDevBundle("1.20.2-R0.1-SNAPSHOT")
    compileOnly("de.tr7zw:item-nbt-api-plugin:2.11.3")

    // Include all the nms sub-modules
    val multiVersionProjects = project(":multiversion").dependencyProject.subprojects
    multiVersionProjects.forEach { compileOnly(it) }
}

tasks {
    build {
        dependsOn(processResources)
    }
}
