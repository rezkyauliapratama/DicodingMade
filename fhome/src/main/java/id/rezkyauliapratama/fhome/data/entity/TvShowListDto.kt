package id.rezkyauliapratama.fhome.data.entity


import com.squareup.moshi.Json
import id.rezkyauliapratama.fhome.domain.entity.TvShowModel

data class ListTvShowtDto(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<TvShowDtoBean>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)


data class TvShowDtoBean(
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "first_air_date")
    val firstAirDate: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "origin_country")
    val originCountry: List<String>,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_name")
    val originalName: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
)


fun TvShowDtoBean.mapToTvShowDomain(): TvShowModel =
    TvShowModel(
        id = id,
        backdropPath = backdropPath,
        genreIds = genreIds,
        originalTitle = name,
        overview = overview,
        popularity = voteAverage,
        posterPath = posterPath,
        releaseDate = firstAirDate,
        voteAverage = voteAverage,
        voteCount = voteCount
    )

fun List<TvShowDtoBean>.mapToTvShowDomain(): List<TvShowModel> = map { it.mapToTvShowDomain() }

