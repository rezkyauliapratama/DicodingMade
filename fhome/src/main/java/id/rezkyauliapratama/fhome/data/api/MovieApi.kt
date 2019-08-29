package id.innovation.innovendor.fsample.data.sample

import id.rezkyauliapratama.fhome.data.entity.ListMoviesDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int?
    ): Single<ListMoviesDto>
}
