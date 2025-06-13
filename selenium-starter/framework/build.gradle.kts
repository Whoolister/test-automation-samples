plugins {
    java
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(libs.selenium.api)
    api(libs.selenium.java)
    api(libs.selenium.support)
    api(libs.selenium.firefox.driver)
    api(libs.jakarta.validation.api)
    api(libs.kermit)
    api(libs.kotest.assertions.core)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}