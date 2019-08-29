package id.rezkyauliapratama.fhome.data.entity

import com.squareup.moshi.Json
import id.rezkyauliapratama.fhome.domain.entity.MovieModel
import id.rezkyauliapratama.fhome.domain.entity.TvShowModel


class ListTvShowDto(
    val tvShowsDto: List<TvShowDtoBean>
)

class TvShowDtoBean(
    @Json(name =  "id")
    val id: Long,
    @Json(name =  "overview")
    val overview: String,
    @Json(name =  "popularity")
    val popularity: Double,
    @Json(name =  "poster_path")
    val posterPath: Int,
    @Json(name =  "release_date")
    val releaseDate: String,
    @Json(name =  "title")
    val title: String
)


fun TvShowDtoBean.mapToMovieDomain(): TvShowModel =
    TvShowModel(
        id = id,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title
    )

fun List<TvShowDtoBean>.mapToTvShowDomain(): List<TvShowModel> = map { it.mapToMovieDomain() }
