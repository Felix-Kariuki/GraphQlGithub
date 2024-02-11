@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("com.apollographql.apollo3").version("3.7.3")
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.kspPlugin)

}

apollo {
    service("service") {
        packageName.set("com.flexcode")
    }
}

android {
    namespace = "com.flexcode.graphql.data"
    compileSdk = 34
    buildFeatures {
        buildConfig = true
    }



    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvm.target.get()
    }
}


dependencies{
    implementation(project(":domain"))

    implementation(libs.apollo.runtime)


    api(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.moshi)

}