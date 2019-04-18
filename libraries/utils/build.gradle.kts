plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

dependencies {
    api(Deps.androidx_appcompat)
    api(Deps.androidx_core)
    api(Deps.androidx_material)

    api(Deps.kotlin)
    api(Deps.timber)
    api(Deps.Rx.android)
    api(Deps.Rx.java)

    implementation(Deps.picasso)

    testImplementation(Deps.testlib_junit)
}
