plugins {
    java
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    testImplementation(project(":pageobjects"))
    testImplementation(project(":framework"))
    testImplementation(libs.selenium.firefox.driver)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
}

tasks.test {
    useJUnitPlatform()
}