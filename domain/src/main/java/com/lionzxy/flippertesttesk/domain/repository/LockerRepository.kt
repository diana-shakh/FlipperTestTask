package com.lionzxy.flippertesttesk.domain.repository

import com.lionzxy.flippertesttesk.domain.model.Locker
import kotlinx.coroutines.flow.StateFlow

interface LockerRepository {
    fun getLockers(): StateFlow<List<Locker>>

    fun setKeyForLocker(lockerId: Int, keyId: Int)
}