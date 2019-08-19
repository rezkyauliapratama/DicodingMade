package id.rezkyauliapratama.fhome.ui.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import id.innovation.libsharedata.entity.DetailMovieResult

@Parcelize
data class TvShowResult(
    val id: Long,
    val title: String,
    val popularity: Double,
    val posterPath: Int,
    val releaseDate: String,
    val overview: String
) : Parcelable


fun TvShowResult.intoDetailMovie() = DetailMovieResult(
    id = id,
    title = title,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    overview = overview
)