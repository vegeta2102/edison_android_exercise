plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "jp.speakbuddy.edisonandroidexercise.repository"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":component:core"))
    testImplementation(project(":component:test"))
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation("com.squareup.retrofit2:converter-simplexml:2.7.1")
    implementation("com.squareup.okhttp3:logging-interceptor:3.14.6")
    implementation("com.facebook.stetho:stetho:1.5.1")
    implementation("com.facebook.stetho:stetho-okhttp3:1.5.1")
    implementation("org.apache.commons:commons-lang3:3.4")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    implementation("com.google.code.gson:gson:2.8.6")
    // Timber
    implementation("com.jakewharton.timber:timber:4.7.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.3.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.3.3")
    implementation("com.willowtreeapps.assertk:assertk-jvm:0.22")
    implementation("io.mockk:mockk:1.11.0")
    implementation("io.mockk:mockk-android:1.11.0")
    implementation("io.mockk:mockk-agent-jvm:1.11.0")
    implementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
    implementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    implementation("org.junit.jupiter:junit-jupiter-params:5.7.0")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    implementation("org.junit.vintage:junit-vintage-engine:5.7.0")
    implementation("org.junit.platform:junit-platform-launcher:1.7.0")
    implementation("junit:junit:4.13.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    testImplementation("org.json:json:20210307")
}