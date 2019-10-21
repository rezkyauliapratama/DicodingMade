package id.rezkyauliapratama.fhome.data.api

import id.rezkyauliapratama.dicodingmade.BuildConfig
import id.rezkyauliapratama.fhome.data.entity.ListMoviesDto
import id.rezkyauliapratama.fhome.data.entity.ListTvShowtDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") pageNumber: Int?,
        @Query("language") language: String = "en-US"
    ): Single<ListMoviesDto>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") pageNumber: Int?,
        @Query("language") language: String = "en-US"
    ): Single<ListTvShowtDto>


    @GET("search/movie")
    fun getMoviesSearch(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") pageNumber: Int?,
        @Query("language") language: String = "en-US",
        @Query("query") query: String
    ): Single<ListMoviesDto>

    @GET("search/tv")
    fun getTvShowsSearch(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") pageNumber: Int?,
        @Query("language") language: String = "en-US",
        @Query("query") query: String
    ): Single<ListTvShowtDto>
}
