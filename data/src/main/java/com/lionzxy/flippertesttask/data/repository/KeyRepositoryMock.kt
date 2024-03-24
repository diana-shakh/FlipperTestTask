package com.lionzxy.flippertesttask.data.repository

import com.lionzxy.flippertesttask.core.di.AppGraph
import com.lionzxy.flippertesttesk.domain.model.Key
import com.lionzxy.flippertesttesk.domain.repository.KeyRepository
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ContributesBinding(AppGraph::class)
class KeyRepositoryMock @Inject constructor() : KeyRepository {
    private val keys = MutableStateFlow(
        (0..15).map { Key(it) }
    )

    override fun getKeys() = keys
}