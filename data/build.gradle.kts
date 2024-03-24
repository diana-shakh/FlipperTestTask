plugins {
    id("flipper.android-lib")
    id("flipper.anvil")
}

android.namespace = "com.lionzxy.flippertesttask.data"

dependencies {
    implementation(projects.domain)
    implementation(projects.components.core.di)

    implementation(libs.kotlin.coroutines)
}