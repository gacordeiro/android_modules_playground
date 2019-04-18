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

    implementation(Deps.picasso)
    implementation(Deps.timber)

    implementation(Deps.Dagger.androidSupport)
    kapt(Deps.Dagger.androidProcessor)
    implementation(Deps.Dagger.dagger)
    kapt(Deps.Dagger.daggerCompiler)

    implementation(Deps.Rx.android)

    implementation(Deps.androidx_appcompat)
    implementation(Deps.androidx_core)
    implementation(Deps.androidx_constraintlayout)
    implementation(Deps.androidx_material)
    implementation(Deps.androidx_recyclerview)

    testImplementation(Deps.testlib_junit)

    androidTestImplementation(Deps.testandroidx_runner)
    androidTestImplementation(Deps.testandroidx_rules)
    androidTestImplementation(Deps.testandroidx_espressocore)
}
