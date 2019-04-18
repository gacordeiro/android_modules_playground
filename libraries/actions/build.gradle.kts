plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

dependencies {
    implementation(Deps.kotlin)
    testImplementation(Deps.testlib_junit)
}
