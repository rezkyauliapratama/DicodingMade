package id.rezkyauliapratama.dicodingmade.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.rezkyauliapratama.dicodingmade.domain.entity.mapToTvShowList
import id.rezkyauliapratama.dicodingmade.domain.repository.MovieRepository
import id.rezkyauliapratama.dicodingmade.presenter.entity.TvShowResult
import io.reactivex.Single
import javax.inject.Inject

class TvShowUsecase @Inject constructor(
    private val movieRepository: MovieRepository,
    errorTransformer: ErrorTransformer<List<TvShowResult>>
) : SingleUseCase<List<TvShowResult>>(errorTransformer) {

    companion object {
        const val pageNum: String = "pageNum"
    }

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<List<TvShowResult>> {
        val pageNumber: Int = data[pageNum] as Int
        return movieRepository.getTvShows(pageNumber).map { it.mapToTvShowList() }
    }

}