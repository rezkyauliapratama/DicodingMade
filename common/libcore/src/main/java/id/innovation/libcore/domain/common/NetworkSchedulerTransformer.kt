package id.innovation.libcore.domain.common

import id.innovation.libcore.domain.executors.PostExecutionThread
import id.innovation.libcore.domain.executors.PreExecutionThread
import id.innovation.libcore.domain.executors.ThreadExecutor
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NetworkSchedulerTransformer<T> @Inject constructor(
    private val preExecutionThread: PreExecutionThread,
    private val postExecutionThread: PostExecutionThread
) : SingleTransformer<T, T> {


    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.subscribeOn(preExecutionThread.scheduler)
            .observeOn(postExecutionThread.scheduler)
    }
}
