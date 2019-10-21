package id.rezkyauliapratama.dicodingmade.data.entity.favorite.movie

import com.squareup.moshi.Json
import id.rezkyauliapratama.dicodingmade.domain.entity.MovieModel

data class DetailMovieDto(
    @Json(name = "adult")
    val adult: Boolean = false,
    @Json(name = "backdrop_path")
    val backdropPath: String = "",
    @Json(name = "belongs_to_collection")
    val belongsToCollection: Any? = Any(),
    @Json(name = "budget")
    val budget: Int = 0,
    @Json(name = "genres")
    val genres: List<Genre> = listOf(),
    @Json(name = "homepage")
    val homepage: String? = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "imdb_id")
    val imdbId: String = "",
    @Json(name = "original_language")
    val originalLanguage: String = "",
    @Json(name = "original_title")
    val originalTitle: String = "",
    @Json(name = "overview")
    val overview: String = "",
    @Json(name = "popularity")
    val popularity: Double = 0.0,
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompany> = listOf(),
    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountry> = listOf(),
    @Json(name = "release_date")
    val releaseDate: String = "",
    @Json(name = "revenue")
    val revenue: Int = 0,
    @Json(name = "runtime")
    val runtime: Int = 0,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguage> = listOf(),
    @Json(name = "status")
    val status: String = "",
    @Json(name = "tagline")
    val tagline: String = "",
    @Json(name = "title")
    val title: String = "",
    @Json(name = "video")
    val video: Boolean = false,
    @Json(name = "vote_average")
    val voteAverage: Double = 0.0,
    @Json(name = "vote_count")
    val voteCount: Int = 0
)

fun DetailMovieDto.mapToDomain(): MovieModel =
    MovieModel(
        id = id,
        backdropPath = backdropPath,
        genreIds = listOf(),
        originalTitle = originalTitle,
        overview = overview,
        popularity = voteAverage,
        posterPath = posterPath,
        releaseDate = releaseDate,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )

