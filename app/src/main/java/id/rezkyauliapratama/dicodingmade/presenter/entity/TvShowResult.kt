package id.rezkyauliapratama.dicodingmade.presenter.entity

data class TvShowResult(
    val id: Int,
    val genreIds: List<Int>,
    val originalTitle: String,
    val popularity: Double,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val overview: String = ""
)