package id.rezkyauliapratama.fhome.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.rezkyauliapratama.fhome.domain.entity.mapToTvShowList
import id.rezkyauliapratama.fhome.domain.repository.MovieRepository
import id.rezkyauliapratama.fhome.ui.entity.TvShowResult
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