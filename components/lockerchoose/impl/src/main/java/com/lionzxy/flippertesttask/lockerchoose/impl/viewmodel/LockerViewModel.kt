package com.lionzxy.flippertesttask.lockerchoose.impl.viewmodel

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.lionzxy.flippertesttask.lockerchoose.impl.config.LockerModel
import com.lionzxy.flippertesttesk.domain.repository.LockerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class LockerViewModel @Inject constructor(private val lockerRepository: LockerRepository) :
    InstanceKeeper.Instance {
    private var lockers: MutableStateFlow<List<LockerModel>> = MutableStateFlow(emptyList())

    private val viewModelJob: Job = CoroutineScope(Dispatchers.IO).launch {
        lockerRepository.getLockers()
            .map { list ->
                list.map { LockerModel(it.id, it.keyId) }
            }.collectLatest {
                lockers.value = it
            }
    }

    fun getLockers() = lockers.asStateFlow()

    override fun onDestroy() {
        super.onDestroy()
        viewModelJob.cancel()
    }
}