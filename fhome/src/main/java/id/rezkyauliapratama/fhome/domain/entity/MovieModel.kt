package id.rezkyauliapratama.fhome.domain.entity

import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult

data class MovieModel(
    val id: Long,
    val title: String,
    val popularity: Double,
    val overview: String,
    val posterPath: Int,
    val releaseDate: String
)

fun MovieModel.mapToPopularMovie(): PopularMovieResult =
    PopularMovieResult(
        id = id,
        title = title,
        releaseDate = releaseDate,
        posterPath = posterPath,
        popularity = popularity,
        overview = overview
    )

fun List<MovieModel>.mapToPopularMovieList() : List<PopularMovieResult> = map { it.mapToPopularMovie() }

