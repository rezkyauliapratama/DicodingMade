package id.rezkyauliapratama.fhome.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.rezkyauliapratama.fhome.domain.entity.mapToTvShowList
import id.rezkyauliapratama.fhome.domain.repository.MovieRepository
import id.rezkyauliapratama.fhome.ui.entity.TvShowResult
import io.reactivex.Single
import javax.inject.Inject

class TvShowUsecase @Inject constructor(
    private val movieRepository: MovieRepository
) : SingleUseCase<List<TvShowResult>>() {

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<List<TvShowResult>> {
        return movieRepository.getTvShows().map { it.mapToTvShowList() }
    }

}