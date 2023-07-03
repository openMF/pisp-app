package org.mifos.openbanking.viewModel.base

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job

open class BaseViewModel {

    // ASYNC - COROUTINES
    protected val job: Job = Job()
    protected val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun onClear() {
        job.cancel()
    }
}
