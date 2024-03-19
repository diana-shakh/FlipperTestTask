package com.lionzxy.flippertesttask.keychoose.impl.viewmodel

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.lionzxy.flippertesttask.keychoose.impl.config.KeyModel
import kotlinx.collections.immutable.toPersistentSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class KeyViewModel @Inject constructor() : InstanceKeeper.Instance {
    private val keySet = MutableStateFlow(
        (0..15).map { KeyModel(it) }.toPersistentSet()
    )

    fun getKeys() = keySet.asStateFlow()
}