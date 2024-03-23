package com.lionzxy.flippertesttask.main.config

import kotlinx.serialization.Serializable

@Serializable
sealed class MainConfig {
    abstract val enum: MainEnum

    @Serializable
    data object BottomBar : MainConfig() {
        override val enum = MainEnum.BOTTOM_BAR
    }

    @Serializable
    data class KeyChoose(val lockerId: Int) : MainConfig() {
        override val enum = MainEnum.KEY_CHOOSE
    }
}