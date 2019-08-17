package id.rezkyauliapratama.fhome.data.source.mock

import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import id.rezkyauliapratama.fhome.data.mock.PopularMovieMockDataFactory
import id.rezkyauliapratama.fhome.data.mock.TvShowMockDataFactory
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class MovieMockDataSourceImpl : MovieMockDataSource {

    override fun getTvShows(): Single<List<MovieDtoBean>> = Single.just(
        TvShowMockDataFactory.create()
    ).map {
        it.moviesDto
    }.delay(3, TimeUnit.SECONDS)

    override fun getPopularMovies(): Single<List<MovieDtoBean>> = Single.just(
        PopularMovieMockDataFactory.create()
    ).map {
        it.moviesDto
    }.delay(3, TimeUnit.SECONDS)

}