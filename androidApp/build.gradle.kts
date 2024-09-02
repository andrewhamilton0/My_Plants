plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization") version "2.0.0"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.5.0"
    id("androidx.baselineprofile")
}

composeCompiler {
    enableStrongSkippingMode = true
}

android {
    namespace = "com.example.myplants.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.myplants.android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        create("benchmark") {
            initWith(buildTypes.getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.apply {
    getByPath("preBuild").dependsOn(ktlintFormat)
}

ktlint {
    ignoreFailures.set(false)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
    }
    filter {
        exclude {
            it.file.path.contains("generated/")
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.6.8")
    implementation("androidx.compose.ui:ui-tooling:1.6.8")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.8")
    implementation("androidx.compose.foundation:foundation:1.6.8")
    implementation("androidx.compose.material:material:1.6.8")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.navigation:navigation-compose:2.8.0-beta03")
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("androidx.profileinstaller:profileinstaller:1.3.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.8")
    "baselineProfile"(project(":baselineprofile"))
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.8")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    with(Deps.Koin) {
        implementation(core)
        implementation(android)
        implementation(androidXCompose)
    }
    with(Deps) {
        implementation(kotlinDateTime)
    }
}
