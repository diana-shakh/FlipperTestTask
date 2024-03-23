package com.lionzxy.flippertesttask.main.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.flipperdevices.core.decompose.DecomposeComponent
import com.lionzxy.flippertesttask.main.config.MainConfig
import com.lionzxy.flippertesttask.main.config.MainEnum

@Composable
fun ComposableMainScreen(
    childStack: ChildStack<MainConfig, DecomposeComponent>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,

    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            Children(
                stack = childStack,
            ) {
                it.instance.Render()
            }
        }
    }
}