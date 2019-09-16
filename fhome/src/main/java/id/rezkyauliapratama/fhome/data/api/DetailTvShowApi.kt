package id.rezkyauliapratama.fhome.data.api

import id.rezkyauliapratama.dicodingmade.BuildConfig
import id.rezkyauliapratama.fhome.data.entity.favorite.tvshow.DetailTvDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailTvShowApi {
    @GET("tv/{tvId}")
    fun getDetailTvShow(
        @Path("tvId") tvId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US"
    ): Single<DetailTvDto>

}