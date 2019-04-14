import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

androidExtensions {
    configure(delegateClosureOf<AndroidExtensionsExtension> { isExperimental = true })
}

dependencies {
    implementation(project(Deps.Libs.actions))
    implementation(project(Deps.Libs.tracking))

    implementation(Deps.kotlin)
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
