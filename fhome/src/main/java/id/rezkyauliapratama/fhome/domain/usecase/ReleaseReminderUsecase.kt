package id.rezkyauliapratama.fhome.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.rezkyauliapratama.dicodingmade.domain.entity.mapToTvShowList
import id.rezkyauliapratama.dicodingmade.domain.repository.MovieRepository
import id.rezkyauliapratama.dicodingmade.presenter.entity.TvShowResult
import id.rezkyauliapratama.fhome.domain.repository.AlarmRepository
import io.reactivex.Single
import javax.inject.Inject

class ReleaseReminderUsecase @Inject constructor(
    private val alarmRepository: AlarmRepository,
    errorTransformer: ErrorTransformer<Boolean>
) : SingleUseCase<Boolean>(errorTransformer) {

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<Boolean> {
        return Single.just(alarmRepository.isReleaseReminderActivate())
    }

}