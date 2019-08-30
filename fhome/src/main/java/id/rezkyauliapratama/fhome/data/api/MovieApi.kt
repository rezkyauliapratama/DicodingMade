package id.rezkyauliapratama.fhome.data.api

import id.rezkyauliapratama.fhome.data.entity.ListMoviesDto
import id.rezkyauliapratama.fhome.data.entity.ListTvShowtDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int?
    ): Single<ListMoviesDto>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int?
    ): Single<ListTvShowtDto>


}
