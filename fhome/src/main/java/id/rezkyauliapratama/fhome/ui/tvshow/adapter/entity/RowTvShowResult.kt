package id.rezkyauliapratama.fhome.ui.tvshow.adapter.entity

import id.rezkyauliapratama.dicodingmade.BuildConfig
import id.rezkyauliapratama.dicodingmade.presenter.entity.PopularMovieResult
import id.rezkyauliapratama.dicodingmade.presenter.entity.TvShowResult
import javax.inject.Inject

class RowTvShowResult(private val tvShowResult: TvShowResult) {
    fun getId() = tvShowResult.id
    fun getOriginalTitle() = tvShowResult.originalTitle
    fun getVoteAverage() = tvShowResult.popularity.toString()
    fun getThumnailImage() = StringBuilder().append(BuildConfig.IMAGE_BASE_URL)
        .append(tvShowResult.posterPath).toString()

    class Factory @Inject constructor() {
        fun create(tvShowResult: TvShowResult): RowTvShowResult {
            return RowTvShowResult(tvShowResult)
        }
    }
}
