plugins {
    id ("com.android.library")
    id ("kotlin-android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk  =33

    defaultConfig {
        minSdk = 21
        targetSdk = 33


        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles( "consumer-rules.pro")
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
        sourceCompatibility =JavaVersion.VERSION_1_8
        targetCompatibility =JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":search:search_domain"))
    implementation(project(":common:common_utils"))
    implementation (Dependencies.core)
    implementation (Dependencies.appCompat)
    implementation (Dependencies.androidMaterial)
    implementation (Dependencies.constraintLayout)
    testImplementation (TestImplementation.junit)
    androidTestImplementation (AndroidTestImplementation.junit)
    androidTestImplementation (AndroidTestImplementation.espresso)

    implementation(DaggerHilt.hilt)
    kapt(DaggerHilt.hiltAndroidCompiler)
    kapt(DaggerHilt.hiltCompiler)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.gsonConvertor)
    implementation(Retrofit.okHttp)

}