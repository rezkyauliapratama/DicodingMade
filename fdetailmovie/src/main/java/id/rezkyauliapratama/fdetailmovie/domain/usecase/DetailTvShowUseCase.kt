package id.rezkyauliapratama.fdetailmovie.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.innovation.libsharedata.entity.DetailMovieResult
import id.rezkyauliapratama.fdetailmovie.domain.entity.mapToResult
import id.rezkyauliapratama.fdetailmovie.domain.repository.DetailContentRepository
import io.reactivex.Single
import javax.inject.Inject

class DetailTvShowUseCase @Inject constructor(
    private val detailContentRepository: DetailContentRepository,
    errorTransformer: ErrorTransformer<DetailMovieResult>
) : SingleUseCase<DetailMovieResult>(errorTransformer) {

    companion object {
        const val tvShowId: String = "tvShowId"
    }

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<DetailMovieResult> {
        val tvShowId: Int = data[tvShowId] as Int
        return detailContentRepository.getTvShowMovie(tvShowId).map {
            it.mapToResult()
        }
    }


}