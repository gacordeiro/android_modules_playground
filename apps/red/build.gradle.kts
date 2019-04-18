plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    defaultConfig {
        applicationId = "com.tutuland.app_red" //This does not have to be the same as the package on the manifest
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
        }
    }
}

dependencies {
    implementation(project(Deps.Feats.details))
    implementation(project(Deps.Feats.List.view))
    implementation(project(Deps.Feats.List.presentation))
    implementation(project(Deps.Feats.List.source_pokemon))

    implementation(project(Deps.Libs.actions))
    implementation(project(Deps.Libs.cards))
    implementation(project(Deps.Libs.tracking))
    implementation(project(Deps.Libs.utils))

    implementation(fileTree(mapOf("dir" to "libs", "include" to "*.jar")))
    implementation(Deps.kotlin)
    implementation(Deps.timber)

    implementation(Deps.Rx.android)
    implementation(Deps.Rx.java)

    implementation(Deps.androidx_appcompat)
    implementation(Deps.androidx_core)
    implementation(Deps.androidx_constraintlayout)
    implementation(Deps.androidx_material)
    implementation(Deps.androidx_recyclerview)

    implementation(Deps.Dagger.androidSupport)
    kapt(Deps.Dagger.androidProcessor)
    implementation(Deps.Dagger.dagger)
    kapt(Deps.Dagger.daggerCompiler)

    testImplementation(Deps.testlib_junit)
}
