package com.lionzxy.flippertesttask.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext

abstract class MainDecomposeComponent {
    @Composable
    abstract fun Render(modifier: Modifier)
    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext
        ): MainDecomposeComponent
    }
}
