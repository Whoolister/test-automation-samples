plugins {
    java
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":framework"))
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}