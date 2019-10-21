package id.rezkyauliapratama.fhome.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.rezkyauliapratama.fhome.domain.repository.AlarmRepository
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject


class SetReleaseReminderUsecase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    errorTransformer: ErrorTransformer<Boolean>
) : SingleUseCase<Boolean>(errorTransformer) {

    companion object {
        const val IS_ACTIVATE = "ISACTIVATE"
    }

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<Boolean> {
        val isActivate = data[IS_ACTIVATE] as Boolean
        Timber.e("isActivate : $isActivate")
        return Single.just(alarmRepository.setReleaseReminder(isActivate))
    }

}