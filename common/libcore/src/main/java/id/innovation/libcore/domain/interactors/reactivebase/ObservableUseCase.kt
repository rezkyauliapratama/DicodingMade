package id.innovation.libcore.domain.interactors.reactivebase

import id.innovation.libcore.domain.common.SchedulerTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer


abstract class ObservableUseCase<RESULT>(private val errorTransformer: ObservableTransformer<in RESULT, out RESULT>) {

    abstract fun buildUseCaseObservable(data: Map<String, Any?> = emptyMap()): Observable<RESULT>

    fun execute(data: Map<String, Any?> = emptyMap()): Observable<RESULT> {
        return buildUseCaseObservable(data)
            .compose(errorTransformer)
            .compose(SchedulerTransformer())
    }
}
