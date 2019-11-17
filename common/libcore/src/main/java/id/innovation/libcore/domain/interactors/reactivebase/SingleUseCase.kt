package id.innovation.libcore.domain.interactors.reactivebase

import id.innovation.libcore.domain.common.NetworkSchedulerTransformer
import id.innovation.libcore.errorhandler.ErrorTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import javax.inject.Inject

abstract class SingleUseCase<RESULT> {
    @Inject
    lateinit var networkSchedulerTransformer: NetworkSchedulerTransformer<RESULT>

    @Inject
    lateinit var errorApiTransformer: ErrorTransformer<RESULT>

    abstract fun buildUseCaseSingle(data: Map<String, Any?> = emptyMap()): Single<RESULT>

    fun execute(data: Map<String, Any?> = emptyMap()): Single<RESULT> {
        return buildUseCaseSingle(data)
            .compose(errorApiTransformer)
            .compose(networkSchedulerTransformer)
    }
}
