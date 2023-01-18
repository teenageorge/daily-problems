import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "me.teenageorge"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.24")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    // flatmapjson
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
    // tempfilecsv
    implementation("org.springframework:spring-core:6.0.3")

    annotationProcessor("org.projectlombok:lombok:1.18.24")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<ShadowJar> {
    archiveFileName.set("daily-problems.jar")
}
