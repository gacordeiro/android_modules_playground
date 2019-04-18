import org.gradle.api.JavaVersion

object Config {
    const val minSdk = 21
    const val compileSdk = 28
    const val targetSdk = 28
    val javaVersion = JavaVersion.VERSION_1_8
    const val buildTools = "28.0.3"
}

object Versions {
    const val androidx_appcompat = "1.0.2"
    const val androidx_core = "1.0.1"
    const val androidx_recyclerview = "1.0.0"
    const val androidx_constraintLayout = "1.1.3"
    const val material = "1.1.0-alpha04"

    const val junit = "4.12"
    const val androidx_espresso = "3.1.0"
    const val androidx_testing = "1.1.1"

    const val gradleandroid = "3.4.0"
    const val kotlin = "1.3.30"

    const val picasso = "2.5.2"
    const val pokemon = "1.0.18"
    const val timber = "4.7.1"

    object Dagger {
        const val baseLib = "2.21"
    }

    object Rx {
        const val android = "2.1.0"
        const val binding = "2.2.0"
        const val java = "2.2.3"
    }
}

object Deps {

    object Feats {
        const val details = ":features:details"

        object List {
            const val contract = ":features:list"
            const val presentation = ":features:list:list_presentation"
            const val source_got = ":features:list:list_source_got"
            const val source_pokemon = ":features:list:list_source_pokemon"
            const val view = ":features:list:list_view"
        }
    }

    object Libs {
        const val actions = ":libraries:actions"
        const val cards = ":libraries:data:cards"
        const val tracking = ":libraries:tracking"
        const val utils = ":libraries:utils"
    }

    const val tools_gradleandroid = "com.android.tools.build:gradle:${Versions.gradleandroid}"
    const val tools_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core}"
    const val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintLayout}"
    const val androidx_material = "com.google.android.material:material:${Versions.material}"
    const val androidx_recyclerview = "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val pokemon = "io.pokemontcg:pokemon-tcg-sdk-kotlin:${Versions.pokemon}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    object Dagger {
        const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.Dagger.baseLib}"
        const val androidProcessor = "com.google.dagger:dagger-android-processor:${Versions.Dagger.baseLib}"
        const val dagger = "com.google.dagger:dagger:${Versions.Dagger.baseLib}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.Dagger.baseLib}"
    }

    object Rx {
        const val android = "io.reactivex.rxjava2:rxandroid:${Versions.Rx.android}"
        const val binding = "com.jakewharton.rxbinding2:rxbinding:${Versions.Rx.binding}"
        const val bindingAppCompat = "com.jakewharton.rxbinding2:rxbinding-appcompat-v7:${Versions.Rx.binding}"
        const val bindingSupport = "com.jakewharton.rxbinding2:rxbinding-support-v4:${Versions.Rx.binding}"
        const val java = "io.reactivex.rxjava2:rxjava:${Versions.Rx.java}"
    }

    //Unit Testing *****************************************************************************************************
    const val testlib_junit = "junit:junit:${Versions.junit}"

    //Android Testing **************************************************************************************************
    const val testandroidx_rules = "androidx.test:rules:${Versions.androidx_testing}"
    const val testandroidx_runner = "androidx.test:runner:${Versions.androidx_testing}"
    const val testandroidx_espressocore = "androidx.test.espresso:espresso-core:${Versions.androidx_espresso}"
}

