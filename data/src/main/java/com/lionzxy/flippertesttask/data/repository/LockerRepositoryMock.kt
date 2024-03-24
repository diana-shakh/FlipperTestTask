package com.lionzxy.flippertesttask.data.repository

import com.lionzxy.flippertesttask.core.di.AppGraph
import com.lionzxy.flippertesttesk.domain.model.Locker
import com.lionzxy.flippertesttesk.domain.repository.LockerRepository
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ContributesBinding(AppGraph::class)
class LockerRepositoryMock @Inject constructor() : LockerRepository {
    private val lockers = MutableStateFlow(
        (0..15).map { Locker(it, null) }
    )

    override fun getLockers() = lockers

    override fun setKeyForLocker(lockerId: Int, keyId: Int) {
        lockers.value = lockers.value
            .map {
                if (it.id == lockerId) {
                    it.copy(keyId = keyId)
                } else {
                    it
                }
            }
    }
}