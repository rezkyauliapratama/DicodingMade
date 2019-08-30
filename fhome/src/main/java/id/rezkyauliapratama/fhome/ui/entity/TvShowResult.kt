package id.rezkyauliapratama.fhome.ui.entity

data class TvShowResult(
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