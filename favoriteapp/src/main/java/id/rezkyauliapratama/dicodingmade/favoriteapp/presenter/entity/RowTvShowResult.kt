package id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.entity

import id.rezkyauliapratama.dicodingmade.favoriteapp.BuildConfig
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
