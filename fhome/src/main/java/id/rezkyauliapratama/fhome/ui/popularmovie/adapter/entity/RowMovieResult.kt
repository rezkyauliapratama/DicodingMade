package id.rezkyauliapratama.fhome.ui.popularmovie.adapter.entity

import id.rezkyauliapratama.dicodingmade.BuildConfig
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import javax.inject.Inject

class RowMovieResult(private val popularMovieResult: PopularMovieResult) {
    fun getId() = popularMovieResult.id
    fun getOriginalTitle() = popularMovieResult.originalTitle
    fun getVoteAverage() = popularMovieResult.popularity.toString()
    fun getThumnailImage() = StringBuilder().append(BuildConfig.IMAGE_BASE_URL)
        .append(popularMovieResult.posterPath).toString()

    class Factory @Inject constructor() {
        fun create(popularMovieResult: PopularMovieResult): RowMovieResult {
            return RowMovieResult(popularMovieResult)
        }
    }
}
