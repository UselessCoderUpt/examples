package com.examples.domain.use_case

import com.examples.common.Resource
import com.examples.domain.data.PawnItem
import com.examples.network.data.toPawnItem
import com.examples.network.repository.PawnRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

//UC - Use Case
class GetTodaysRenewalUC @Inject constructor(
    private val repository: PawnRepository
) {
    operator fun invoke(): Flow<Resource<List<PawnItem>>> {
        return repository.getTodaysRenewalList()
    }
}
