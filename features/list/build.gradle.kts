plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

dependencies {
    api(project(Deps.Libs.cards))
    api(project(Deps.Libs.utils))
}
