package id.rezkyauliapratama.fhome.data.entity.favorite.tvshow


import com.squareup.moshi.Json

data class Season(
    @Json(name = "air_date")
    val airDate: String = "",
    @Json(name = "episode_count")
    val episodeCount: Int = 0,
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "overview")
    val overview: String? = "",
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "season_number")
    val seasonNumber: Int? = 0
)