plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}


android {
    namespace = "com.myproj.a8147350assignment2"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.myproj.a8147350assignment2"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    testImplementation(libs.junit)
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.17")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
// Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
// Hilt
    implementation("com.google.dagger:hilt-android:2.59.2")
    ksp("com.google.dagger:hilt-android-compiler:2.59.2")
// ViewModel and coroutines
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.4")
    implementation("androidx.activity:activity-ktx:1.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

}