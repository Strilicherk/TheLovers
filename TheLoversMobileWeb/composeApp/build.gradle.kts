import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget()

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }
    
    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            api(libs.datastore.preferences)
            api(libs.datastore)
            // Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            // Core
            implementation(libs.androidx.core.ktx)
            // Ktor
            implementation(libs.bundles.ktor.android)
            // Lifecycle and ViewModel
            implementation(libs.lifecycle.viewmodel)
        }

        commonMain.dependencies {
            // Compose Multiplatform
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.multiplatform.settings)
            implementation(libs.tink.android)
            // JSON Serialization
            implementation(libs.kotlinx.serialization.json)
            // Lifecycle and ViewModel
            implementation(libs.androidx.lifecycle.runtime.compose)
            // Navigation
            implementation(libs.navigation.compose)
            // Datetime
            implementation(libs.kotlinx.datetime)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            // Ktor
            implementation(libs.bundles.ktor.common)
            // Room
            implementation(libs.room.runtime)
            implementation(libs.room.ktx)
            implementation(libs.room.paging)
        }

        wasmJsMain.dependencies {
            // Coroutines (WebAssembly)
            implementation(libs.kotlinx.coroutines.core)
            // Ktor
            implementation(libs.bundles.ktor.js)
        }

    }
}

android {
    namespace = "org.example.thelovers"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.example.thelovers"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

dependencies {
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.runtime.android)
    implementation(libs.identity.jvm)
    debugImplementation(libs.androidx.ui.tooling)
    // Room
    add("kspAndroid", libs.room.compiler)
}
