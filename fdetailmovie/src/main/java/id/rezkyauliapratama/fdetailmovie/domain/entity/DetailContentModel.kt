package id.rezkyauliapratama.fdetailmovie.domain.entity

import id.innovation.libsharedata.entity.DetailMovieResult

data class DetailContentModel(
    val id: Int,
    val title: String,
    val popularity: Double,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val overview: String
)

fun DetailContentModel.mapToResult(): DetailMovieResult =
    DetailMovieResult(
        id = id,
        title = title,
        popularity = popularity,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        overview = overview
    )
