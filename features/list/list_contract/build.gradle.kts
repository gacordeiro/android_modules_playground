plugins {
    id("com.android.library")
}

dependencies {
    implementation(project(Deps.Libs.cards))
    implementation(project(Deps.Libs.utils))

    implementation(Deps.kotlin)
    implementation(Deps.Rx.java)
}
