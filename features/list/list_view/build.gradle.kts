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
    api(project(Deps.Feats.List.contract))
    api(project(Deps.Feats.List.presentation))//TODO remove this dependencies later and provide via injection
    api(project(Deps.Feats.List.source_got))//TODO remove this dependencies later and provide via injection

    implementation(project(Deps.Libs.actions))
    implementation(project(Deps.Libs.cards))
    implementation(project(Deps.Libs.tracking))
    implementation(project(Deps.Libs.utils))

    implementation(Deps.kotlin)
    implementation(Deps.picasso)
    implementation(Deps.timber)

    implementation(Deps.Rx.android)
    implementation(Deps.Rx.java)

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
