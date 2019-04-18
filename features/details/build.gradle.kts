plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

dependencies {
    implementation(project(Deps.Libs.tracking))

    implementation(Deps.kotlin)
    implementation(Deps.androidx_appcompat)
    implementation(Deps.androidx_core)
    implementation(Deps.androidx_constraintlayout)
    implementation(Deps.androidx_material)
    implementation(Deps.androidx_recyclerview)

    testImplementation(Deps.testlib_junit)
}
