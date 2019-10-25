package id.rezkyauliapratama.dicodingmade.favoriteapp.data.api

import id.rezkyauliapratama.dicodingmade.favoriteapp.BuildConfig
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.entity.movie.DetailMovieDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailMovieApi {
    @GET("movie/{movieId}")
    fun getDetailMovie(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US"
    ): Single<DetailMovieDto>

}