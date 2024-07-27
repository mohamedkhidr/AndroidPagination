plugins {
    id("com.android.application")
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.kapt)
    kotlin("android")
}




    val versions = mapOf(
        "paging"          to "3.3.1",
        "coroutines"      to "1.4.2",
        "retrofit"        to "2.9.0",
        "lifecycleVersion" to "2.2.0",
        "hilt"            to "2.30.1-alpha",
        "core_ktx"        to "1.3.2",
        "moshi"           to "1.11.0",
        "okhttp3_logging" to "4.9.0",
        "coil"            to "1.0.0",
        "room"            to "2.6.1"
    )

android {
    namespace = "com.khidrew.pagination"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.khidrew.pagination"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    dataBinding{
        enable = true
    }

    hilt{
        enableAggregatingTask = false
    }

    kapt {
        correctErrorTypes = true
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation("junit:junit:4.13.1")
    androidTestImplementation ("androidx.test.ext:junit:1.1.2")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.3.0")

    // Paging 3.0
    implementation("androidx.paging:paging-runtime-ktx:${versions["paging"]}")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${versions["retrofit"]}")
    implementation("com.squareup.retrofit2:converter-moshi:${versions["retrofit"]}")
    implementation("com.squareup.moshi:moshi-kotlin:${versions["moshi"]}")
    implementation("com.squareup.moshi:moshi-kotlin-codegen:${versions["moshi"]}")
    implementation("com.squareup.okhttp3:logging-interceptor:${versions["okhttp3_logging"]}")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions["coroutines"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions["coroutines"]}")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${versions["lifecycleVersion"]}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${versions["lifecycleVersion"]}")

    //Dagger - Hilt
    implementation(libs.hilt.android)
    kapt (libs.hilt.android.compiler)

    kapt (libs.androidx.hilt.compiler)

    // coil
    implementation("io.coil-kt:coil:${versions["coil"]}")

    // room
    implementation("androidx.room:room-runtime:${versions["room"]}")
    implementation("androidx.room:room-ktx:${versions["room"]}")
    implementation("androidx.room:room-paging:${versions["room"]}")
    kapt ("androidx.room:room-compiler:${versions["room"]}")
}