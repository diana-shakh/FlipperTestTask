package com.lionzxy.flippertesttesk.domain.repository

import com.lionzxy.flippertesttesk.domain.model.Key
import kotlinx.coroutines.flow.StateFlow

interface KeyRepository {
    fun getKeys(): StateFlow<List<Key>>
}