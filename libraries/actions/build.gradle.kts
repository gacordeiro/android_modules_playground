plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {
    implementation(Deps.kotlin)
    testImplementation(Deps.testlib_junit)
}
