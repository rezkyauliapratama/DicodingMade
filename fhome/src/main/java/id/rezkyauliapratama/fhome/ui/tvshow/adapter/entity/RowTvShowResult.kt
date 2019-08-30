package id.rezkyauliapratama.fhome.ui.tvshow.adapter.entity

import id.rezkyauliapratama.dicodingmade.BuildConfig
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import id.rezkyauliapratama.fhome.ui.entity.TvShowResult
import javax.inject.Inject

class RowTvShowResult(private val tvShowResult: TvShowResult) {
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
