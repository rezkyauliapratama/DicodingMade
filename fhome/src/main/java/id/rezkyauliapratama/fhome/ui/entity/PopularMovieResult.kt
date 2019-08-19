package id.rezkyauliapratama.fhome.ui.entity

import android.os.Parcelable
import id.innovation.libsharedata.entity.DetailMovieResult
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PopularMovieResult(
    val id: Long,
    val title: String,
    val popularity: Double,
    val posterPath: Int,
    val releaseDate: String,
    val overview: String
) : Parcelable

fun PopularMovieResult.intoDetailMovie() = DetailMovieResult(
    id = id,
    title = title,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    overview = overview
)