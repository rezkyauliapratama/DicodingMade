package id.rezkyauliapratama.dicodingmade.data.entity.favorite.tvshow


import com.squareup.moshi.Json

data class Genre(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = ""
)