package org.mifos.openbanking.domain.usecase.base

import org.mifos.openbanking.base.Response

abstract class BaseUseCase<R : BaseRequest, T> {

    suspend fun execute(request: R): Response<T> {
        val validated = request.validate()
        if (validated) return run(request)
        return Response.Error(IllegalArgumentException())
    }

    abstract suspend fun run(request: R): Response<T>
}
