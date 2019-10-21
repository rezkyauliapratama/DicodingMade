package id.rezkyauliapratama.dicodingmade.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.rezkyauliapratama.dicodingmade.domain.entity.mapToPopularMovieList
import id.rezkyauliapratama.dicodingmade.domain.repository.MovieRepository
import id.rezkyauliapratama.dicodingmade.presenter.entity.PopularMovieResult
import io.reactivex.Single
import javax.inject.Inject

class PopularMovieUsecase @Inject constructor(
    private val movieRepository: MovieRepository,
    errorTransformer: ErrorTransformer<List<PopularMovieResult>>
) : SingleUseCase<List<PopularMovieResult>>(errorTransformer) {

    companion object {
        const val pageNum: String = "pageNum"
    }

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<List<PopularMovieResult>> {
        val pageNumber: Int = data[pageNum] as Int
        return movieRepository.getPopularMovies(pageNumber).map { it.mapToPopularMovieList() }
    }

}