package id.rezkyauliapratama.dicodingmade.data.api

import id.rezkyauliapratama.dicodingmade.BuildConfig
import id.rezkyauliapratama.dicodingmade.data.entity.ListMoviesDto
import id.rezkyauliapratama.dicodingmade.data.entity.ListTvShowtDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface MovieApi {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") pageNumber: Int?,
        @Query("language") language: String = "en-US"
    ): Single<ListMoviesDto>

    @GET("discover/movie")
    fun getReleaseMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("primary_release_date.gte") releaseDateGte: String,
        @Query("primary_release_date.lte") releaseDateLte: String,
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
