plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.integradoraiot"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.integradoraiot"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //modelo vista vista-modelo
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("androidx.databinding:databinding-runtime:8.1.0")

    // facebook
    implementation ("com.facebook.android:facebook-applinks:latest.release")
    implementation ("com.facebook.android:facebook-common:latest.release")
    implementation ("com.facebook.android:facebook-core:latest.release")
    implementation ("com.facebook.android:facebook-gamingservices:latest.release")
    implementation ("com.facebook.android:facebook-login:latest.release")
    implementation ("com.facebook.android:facebook-messenger:latest.release")
    implementation ("com.facebook.android:facebook-share:latest.release")
    implementation ("com.facebook.android:facebook-android-sdk:[4,5)")

    // google Firebase

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")

    // Also add the dependency for the Google Play services library and specify its version
    implementation("com.google.android.gms:play-services-auth:21.0.0")

}