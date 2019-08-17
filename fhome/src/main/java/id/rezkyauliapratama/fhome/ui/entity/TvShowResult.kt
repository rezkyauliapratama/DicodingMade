package id.rezkyauliapratama.fhome.ui.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class TvShowResult(
    val id: Long,
    val title: String,
    val popularity: Double,
    val posterPath: Int,
    val releaseDate: String,
    val overview: String
) : Parcelable
