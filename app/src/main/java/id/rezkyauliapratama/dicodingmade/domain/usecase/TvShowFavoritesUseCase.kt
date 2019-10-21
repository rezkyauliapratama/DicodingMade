package id.rezkyauliapratama.dicodingmade.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.rezkyauliapratama.dicodingmade.domain.entity.mapToTvShowList
import id.rezkyauliapratama.dicodingmade.domain.repository.MovieRepository
import id.rezkyauliapratama.dicodingmade.presenter.entity.TvShowResult
import io.reactivex.Single
import javax.inject.Inject


class TvShowFavoritesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    errorTransformer: ErrorTransformer<List<TvShowResult>>
) : SingleUseCase<List<TvShowResult>>(errorTransformer) {

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<List<TvShowResult>> {
        return movieRepository.getTvShowFavorites().map {
            it.mapToTvShowList()
        }
    }

}