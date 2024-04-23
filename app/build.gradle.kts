plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.github1_novgorodov"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.github1_novgorodov"
        minSdk = 29
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
    implementation(libs.appcompat)
    implementation("com.squareup.retrofit2:retrofit:2.3.0")
    implementation("com.squareup.retrofit2:converter-gson:2.3.0")
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}