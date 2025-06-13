import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.20"
}

group = "com.whoolister"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib)
    implementation(libs.jackson.core)
    implementation(libs.jackson.kotlin)
    implementation(libs.jackson.date)
    testImplementation(libs.rest.assured)
    testImplementation(libs.rest.assured.kotlin)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.kotest.assertions.core)
    testRuntimeOnly(libs.junit.jupiter.engine)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_21)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Test> {
    doFirst {
        exec {
            commandLine("bash", "setup.sh")
        }
    }
    doLast {
        exec {
            commandLine("bash", "teardown.sh")
        }
    }
}
