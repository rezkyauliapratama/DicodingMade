package id.rezkyauliapratama.fhome.data.source.mock

import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import id.rezkyauliapratama.fhome.data.mock.MovieMockDataFactory
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class MovieMockDataSourceImpl : MovieMockDataSource {

    override fun getPopularMovies(): Single<List<MovieDtoBean>> = Single.just(
        MovieMockDataFactory.create()
    ).map {
        it.moviesDto
    }.delay(3, TimeUnit.SECONDS)

}