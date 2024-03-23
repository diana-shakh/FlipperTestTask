plugins {
    id("flipper.android-app")
    id("com.squareup.anvil")
    id("kotlin-kapt")
}

android.namespace = "com.lionzxy.flippertesttask"

dependencies {
    implementation(projects.components.core.di)
    implementation(projects.components.core.decompose)

    implementation(projects.components.singleactivity)

    implementation(projects.components.bottombar.api)
    implementation(projects.components.bottombar.impl)
    implementation(projects.components.lockerchoose.api)
    implementation(projects.components.lockerchoose.impl)
    implementation(projects.components.keychoose.api)
    implementation(projects.components.keychoose.impl)
    implementation(projects.components.main.api)
    implementation(projects.components.main.impl)


    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.bundles.decompose)

    implementation(libs.dagger)
    kapt(libs.dagger.kapt)

    implementation(libs.timber)
    implementation(libs.appcompat)
}
