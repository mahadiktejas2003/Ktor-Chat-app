plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
id("org.jetbrains.kotlin.plugin.serialization")


}

android {
    namespace = "com.tejaa.ktorChatAppTejaa"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tejaa.ktorChatAppTejaa"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {

        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/{AL2.0,LGPL2.1,gradle/incremental.annotation.processors}"
        }
    }
}

dependencies {

    implementation(libs.androidx.material3.android)
    dependencies {
        var compose_version="1.0.1"
        implementation("androidx.core:core-ktx:1.7.0")
        implementation("androidx.appcompat:appcompat:1.3.1")
        implementation("com.google.android.material:material:1.4.0")
        implementation("androidx.compose.ui:ui:$compose_version")
        implementation("androidx.compose.material:material:$compose_version")
        implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
        implementation("androidx.activity:activity-compose:1.4.0")
        testImplementation("junit:junit:4.+")
        androidTestImplementation("androidx.test.ext:junit:1.1.3")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
        debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")

        // Compose dependencies
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")
        implementation("androidx.navigation:navigation-compose:2.6.0")

        // Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

        // Coroutine Lifecycle Scopes
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
        implementation("androidx.navigation:navigation-fragment-ktx:2.4.0")
        // Dagger - Hilt
        implementation("com.google.dagger:hilt-android:2.48")
        ksp("com.google.dagger:hilt-android-compiler:2.48")
        ksp("androidx.hilt:hilt-compiler:1.0.0")
        implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha03")


        // Ktor
        var ktor_version = "1.6.3"
        implementation("io.ktor:ktor-client-core:$ktor_version")
        implementation("io.ktor:ktor-client-cio:$ktor_version")
        implementation("io.ktor:ktor-client-serialization:$ktor_version")
        implementation("io.ktor:ktor-client-websockets:$ktor_version")
        implementation("io.ktor:ktor-client-logging:$ktor_version")
        implementation("ch.qos.logback:logback-classic:1.2.6")

        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    }

}