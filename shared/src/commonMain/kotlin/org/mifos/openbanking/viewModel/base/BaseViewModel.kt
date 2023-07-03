package org.mifos.openbanking.viewModel.base

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import org.mifos.openbanking.viewModel.ViewModel
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel() {
    // ASYNC - COROUTINES
    protected val coroutineContext: CoroutineContext = TODO()
    protected val job: Job = Job()
    protected val exceptionHandler = CoroutineExceptionHandler { _, _ -> }
}
