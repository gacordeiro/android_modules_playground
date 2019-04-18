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
    testImplementation(Deps.testlib_junit)
}
