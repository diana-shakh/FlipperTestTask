package com.lionzxy.flippertesttask.keychoose.impl.api

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    @Assisted private val onKeyClicked: () -> Unit,
    private val keyViewModelProvider: Provider<KeyViewModel>
) : KeyChooseDecomposeComponent(componentContext) {
    private val keyViewModel = instanceKeeper.getOrCreate { keyViewModelProvider.get() }

    init {
        keyViewModel.init(keyNumber)
    }

    @Composable
    override fun Render() {
        val keys by keyViewModel.getKeys().collectAsState()
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Blue)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Selecting a key for the locker #$keyNumber",
                fontSize = 32.sp,
                textAlign = TextAlign.Start,
                color = Color.White
            )
            KeyComposableScreen(keys) { keyId ->
                keyViewModel.onKeyChoose(keyId)
                onKeyClicked()
            }
        }


    }

    @AssistedFactory
    @ContributesBinding(AppGraph::class, KeyChooseDecomposeComponent.Factory::class)
    interface Factory : KeyChooseDecomposeComponent.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            onKeyClicked: () -> Unit,
            keyNumber: Int
        ): KeyChooseDecomposeComponentImpl
    }
}