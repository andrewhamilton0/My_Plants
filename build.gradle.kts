import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.*

plugins {
    // trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("8.0.2").apply(false)
    id("com.android.library").version("8.0.2").apply(false)
    kotlin("android").version("1.8.21").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    id("org.jlleitschuh.gradle.ktlint") version "11.5.0"
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

    dependencies {
        // ...
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
    }
}
