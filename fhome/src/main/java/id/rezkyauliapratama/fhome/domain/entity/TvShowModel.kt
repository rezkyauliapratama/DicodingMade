package id.rezkyauliapratama.fhome.domain.entity

import id.rezkyauliapratama.fhome.ui.entity.TvShowResult

data class TvShowModel(
    val id: Long,
    val title: String,
    val popularity: Double,
    val overview: String,
    val posterPath: Int,
    val releaseDate: String
)

fun TvShowModel.mapToTvShows(): TvShowResult =
    TvShowResult(
        id = id,
        title = title,
        releaseDate = releaseDate,
        posterPath = posterPath,
        popularity = popularity,
        overview = overview
    )

fun List<TvShowModel>.mapToTvShowList(): List<TvShowResult> = map { it.mapToTvShows() }

