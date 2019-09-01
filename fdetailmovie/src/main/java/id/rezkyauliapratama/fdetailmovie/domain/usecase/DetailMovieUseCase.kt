package id.rezkyauliapratama.fdetailmovie.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.innovation.libsharedata.entity.DetailMovieResult
import id.rezkyauliapratama.fdetailmovie.domain.entity.mapToResult
import id.rezkyauliapratama.fdetailmovie.domain.repository.DetailContentRepository
import io.reactivex.Single
import javax.inject.Inject

class DetailMovieUseCase @Inject constructor(
    private val detailContentRepository: DetailContentRepository,
    errorTransformer: ErrorTransformer<DetailMovieResult>
) : SingleUseCase<DetailMovieResult>(errorTransformer) {

    companion object {
        const val movieId: String = "movieId"
    }

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<DetailMovieResult> {
        val movieId: Int = data[movieId] as Int
        return detailContentRepository.getDetailMovie(movieId).map {
            it.mapToResult()
        }
    }


}