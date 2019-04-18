plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

dependencies {
    api(project(Deps.Feats.List.contract))
    implementation(project(Deps.Libs.actions))
    implementation(project(Deps.Libs.tracking))

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
