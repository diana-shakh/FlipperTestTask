plugins {
    id("flipper.android-compose")
    id("flipper.anvil")
    id("kotlinx-serialization")
}

android.namespace = "com.lionzxy.flippertesttask.main.impl"

dependencies {
    implementation(projects.components.core.di)
    implementation(projects.components.core.decompose)

    implementation(projects.components.main.api)
    implementation(projects.components.keychoose.api)
    implementation(projects.components.bottombar.api)

    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)

    implementation(libs.bundles.decompose)
    implementation(libs.kotlin.serialization.json)
}
