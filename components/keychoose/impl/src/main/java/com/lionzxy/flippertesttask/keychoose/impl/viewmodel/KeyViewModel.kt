package com.lionzxy.flippertesttask.keychoose.impl.viewmodel

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.lionzxy.flippertesttask.keychoose.impl.config.KeyModel
import com.lionzxy.flippertesttesk.domain.repository.KeyRepository
import com.lionzxy.flippertesttesk.domain.repository.LockerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class KeyViewModel @Inject constructor(
    keyRepository: KeyRepository,
    private val lockerRepository: LockerRepository,
) : InstanceKeeper.Instance {
    private var keys: MutableStateFlow<List<KeyModel>> = MutableStateFlow(emptyList())

    private val viewModelJob = CoroutineScope(Dispatchers.IO).launch {
        keyRepository.getKeys()
            .map { list ->
                list.map { KeyModel(it.id) }
            }.collectLatest {
                keys.value = it
            }
    }

    private var lockerId: Int? = null

    fun init(lockerId: Int) {
        this.lockerId = lockerId
    }

    fun getKeys() = keys.asStateFlow()

    fun onKeyChoose(id: Int) {
        lockerId?.let {
            lockerRepository.setKeyForLocker(lockerId = it, keyId = id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelJob.cancel()
    }
}