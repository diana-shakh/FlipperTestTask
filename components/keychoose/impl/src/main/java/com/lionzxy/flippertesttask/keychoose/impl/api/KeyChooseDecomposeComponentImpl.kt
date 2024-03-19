package com.lionzxy.flippertesttask.keychoose.impl.api

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.lionzxy.flippertesttask.core.di.AppGraph
import com.lionzxy.flippertesttask.keychoose.api.KeyChooseDecomposeComponent
import com.lionzxy.flippertesttask.keychoose.impl.composable.KeyComposableScreen
import com.lionzxy.flippertesttask.keychoose.impl.viewmodel.KeyViewModel
import com.squareup.anvil.annotations.ContributesBinding
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Provider

class KeyChooseDecomposeComponentImpl @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted private val keyNumber: Int,
    private val keyViewModelProvider: Provider<KeyViewModel>
) : KeyChooseDecomposeComponent(componentContext) {
    private val keyViewModel = instanceKeeper.getOrCreate { keyViewModelProvider.get() }

    @Composable
    override fun Render() {
        val keySet by keyViewModel.getKeys().collectAsState()
        Column(
            Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Selecting a key for the locker #$keyNumber",
                fontSize = 32.sp,
                textAlign = TextAlign.Start
            )
            KeyComposableScreen(keySet)
        }


    }

    @AssistedFactory
    @ContributesBinding(AppGraph::class, KeyChooseDecomposeComponent.Factory::class)
    interface Factory : KeyChooseDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            keyNumber: Int
        ): KeyChooseDecomposeComponentImpl
    }
}