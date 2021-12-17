package com.examples.domain.use_case

import com.examples.common.Resource
import com.examples.domain.data.PawnItem
import com.examples.network.repository.IPawnRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//UC - Use Case
class GetTodaysRenewalUC @Inject constructor(
    private val repository: IPawnRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<PawnItem>>> {
        return repository.getTodaysRenewalList()
    }
}
