plugins {
    kotlin("jvm") version "2.1.20"
    `maven-publish`
}
allprojects {
    group = "io.github.lm98"
    version = "1.0-SNAPSHOT"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}