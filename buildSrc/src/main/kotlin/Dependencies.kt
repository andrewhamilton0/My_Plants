
object Versions {
    const val koin = "3.2.0"
    const val kotlinDateTime = "0.4.0"
}

object Deps {

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android ="io.insert-koin:koin-android:${Versions.koin}"
        const val androidXCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }
    const val kotlinDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinDateTime}"
}