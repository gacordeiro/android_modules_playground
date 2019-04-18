plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

dependencies {
    implementation(Deps.kotlin)
    implementation(Deps.picasso)
    implementation(Deps.timber)
    implementation(Deps.Rx.android)
    implementation(Deps.Rx.java)

    api(Deps.androidx_appcompat)
    api(Deps.androidx_core)
    api(Deps.androidx_material)

    testImplementation(Deps.testlib_junit)
}
