package id.rezkyauliapratama.fhome.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.rezkyauliapratama.fhome.domain.repository.AlarmRepository
import io.reactivex.Single
import javax.inject.Inject


class SetDailyReminderUsecase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    errorTransformer: ErrorTransformer<Boolean>
) : SingleUseCase<Boolean>(errorTransformer) {

    companion object {
        const val IS_ACTIVATE = "ISACTIVATE"
    }

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<Boolean> {
        val isActivate = data[IS_ACTIVATE] as Boolean
        return Single.just(alarmRepository.setDailyReminder(isActivate))
    }

}