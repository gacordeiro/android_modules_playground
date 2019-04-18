plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

dependencies {
    implementation(project(Deps.Libs.cards))
    implementation(project(Deps.Libs.utils))

    implementation(Deps.kotlin)
    implementation(Deps.Rx.java)
}
