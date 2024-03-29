package id.innovation.libcore.domain.common

import id.innovation.libcore.domain.executors.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulerTransformer<T> : ObservableTransformer<T, T> {

    @Inject
    lateinit var postExecutionThread: PostExecutionThread

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
    }
}
