package id.rezkyauliapratama.fhome.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.rezkyauliapratama.fhome.domain.entity.mapToPopularMovieList
import id.rezkyauliapratama.fhome.domain.repository.MovieRepository
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import io.reactivex.Single
import javax.inject.Inject

class PopularMovieUsecase @Inject constructor(
    private val movieRepository: MovieRepository
) : SingleUseCase<List<PopularMovieResult>>() {

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<List<PopularMovieResult>> {
        return movieRepository.getPopularMovies().map { it.mapToPopularMovieList() }
    }

}