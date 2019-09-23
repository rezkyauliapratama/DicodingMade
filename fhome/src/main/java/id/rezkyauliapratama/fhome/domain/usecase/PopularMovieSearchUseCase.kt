package id.rezkyauliapratama.fhome.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.rezkyauliapratama.fhome.domain.entity.mapToPopularMovieList
import id.rezkyauliapratama.fhome.domain.repository.MovieRepository
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import io.reactivex.Single
import javax.inject.Inject

class PopularMovieSearchUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    errorTransformer: ErrorTransformer<List<PopularMovieResult>>
) : SingleUseCase<List<PopularMovieResult>>(errorTransformer) {

    companion object {
        const val query: String = "query"
        const val pageNum: String = "pageNum"
    }

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<List<PopularMovieResult>> {
        val query: String = data[query] as String
        val pageNum: Int = data[pageNum] as Int
        return movieRepository.getMoviesSearch(pageNum, query).map { it.mapToPopularMovieList() }
    }

}