package id.rezkyauliapratama.dicodingmade.favoriteapp.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.rezkyauliapratama.dicodingmade.favoriteapp.domain.entity.mapToPopularMovieList
import id.rezkyauliapratama.dicodingmade.favoriteapp.domain.repository.MovieRepository
import id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.entity.PopularMovieResult
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject


class MovieFavoritesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    errorTransformer: ErrorTransformer<List<PopularMovieResult>>
) : SingleUseCase<List<PopularMovieResult>>(errorTransformer) {

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<List<PopularMovieResult>> {
        return movieRepository.getMovieFavorites().map {
            Timber.e("execute : $it")
            it.mapToPopularMovieList()
        }
    }

}