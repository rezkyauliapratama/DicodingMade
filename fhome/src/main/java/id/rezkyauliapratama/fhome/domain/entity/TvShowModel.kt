package id.rezkyauliapratama.fhome.domain.entity

import id.rezkyauliapratama.fhome.ui.entity.TvShowResult

data class TvShowModel(
    val backdropPath: String?,
    val genreIds: List<Int>,
    val originalTitle: String,
    val popularity: Double,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val video: Boolean = true,
    val voteAverage: Double,
    val voteCount: Int
)

fun TvShowModel.mapToTvShows(): TvShowResult =
    TvShowResult(
        genreIds = genreIds,
        originalTitle = originalTitle,
        voteCount = voteCount,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        posterPath = posterPath,
        backdropPath = backdropPath,
        popularity = popularity
    )

fun List<TvShowModel>.mapToTvShowList(): List<TvShowResult> = map { it.mapToTvShows() }

