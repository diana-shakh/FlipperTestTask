package com.lionzxy.flippertesttask.main.api

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import com.flipperdevices.core.decompose.DecomposeComponent
import com.lionzxy.flippertesttask.bottombar.BottomBarDecomposeComponent
import com.lionzxy.flippertesttask.core.di.AppGraph
import com.lionzxy.flippertesttask.keychoose.api.KeyChooseDecomposeComponent
import com.lionzxy.flippertesttask.main.MainDecomposeComponent
import com.lionzxy.flippertesttask.main.composable.ComposableMainScreen
import com.lionzxy.flippertesttask.main.config.MainConfig
import com.squareup.anvil.annotations.ContributesBinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MainDecomposeComponentImpl @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    private val keyChooseDecomposeComponentFactory: KeyChooseDecomposeComponent.Factory,
    private val bottomBarDecomposeComponentFactory: BottomBarDecomposeComponent.Factory
) : MainDecomposeComponent(), ComponentContext by componentContext {
    private val navigation = StackNavigation<MainConfig>()

    private val stack: Value<ChildStack<MainConfig, DecomposeComponent>> =
        childStack(
            source = navigation,
            handleBackButton = true,
            serializer = MainConfig.serializer(),
            initialConfiguration = MainConfig.BottomBar,
            childFactory = ::child,
        )

    @Composable
    @Suppress("NonSkippableComposable")
    override fun Render(modifier: Modifier) {
        val childStack by stack.subscribeAsState()

        ComposableMainScreen(
            childStack = childStack,
            modifier = modifier.fillMaxSize()
        )
    }

    private fun child(
        config: MainConfig,
        componentContext: ComponentContext
    ): DecomposeComponent = when (config) {
        MainConfig.BottomBar -> bottomBarDecomposeComponentFactory(
            componentContext = componentContext,
            onLockerClicked = { lockerId -> navigation.bringToFront(MainConfig.KeyChoose(lockerId)) }
        )

        is MainConfig.KeyChoose -> keyChooseDecomposeComponentFactory(
            componentContext = componentContext,
            onKeyClicked = {  navigation.pop() },
            keyNumber = config.lockerId
        )

    }

    @AssistedFactory
    @ContributesBinding(AppGraph::class, MainDecomposeComponent.Factory::class)
    fun interface Factory : MainDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext
        ): MainDecomposeComponentImpl
    }
}