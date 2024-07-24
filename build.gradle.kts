import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.*

plugins {
    // trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.5.1").apply(false)
    id("com.android.library").version("8.5.1").apply(false)
    kotlin("android").version("2.0.0").apply(false)
    kotlin("multiplatform").version("2.0.0").apply(false)
    kotlin("plugin.serialization") version "2.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.5.0"
    id("com.android.test") version "8.5.1" apply false
    id("androidx.baselineprofile") version "1.2.4" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

repositories {
    mavenCentral()
}

ktlint {
    ignoreFailures.set(false)

    reporters {
        reporter(CHECKSTYLE)
        reporter(PLAIN)
    }
}

buildscript {

    repositories {
        gradlePluginPortal()
    }

    dependencies {
        // ...
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
        classpath("dev.icerock.moko:resources-generator:0.23.0")
    }
}
