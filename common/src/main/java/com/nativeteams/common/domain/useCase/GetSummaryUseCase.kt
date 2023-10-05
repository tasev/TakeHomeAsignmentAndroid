package com.nativeteams.common.domain.useCase

import com.nativeteams.common.data.repository.RemoteRepositoryImpl
import javax.inject.Inject

class GetSummaryUseCase @Inject constructor(
    private val repository: RemoteRepositoryImpl
) {
    operator fun invoke() = repository.getSummary()
}