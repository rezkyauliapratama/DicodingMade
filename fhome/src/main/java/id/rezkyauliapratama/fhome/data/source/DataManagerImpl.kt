package id.rezkyauliapratama.fhome.data.source

import id.rezkyauliapratama.fhome.data.DataManager
import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import id.rezkyauliapratama.fhome.data.source.mock.MovieMockDataSource
import io.reactivex.Single

class DataManagerImpl(
    private val mockDataSource: MovieMockDataSource
) : DataManager {

    override fun getTvShows(): Single<List<MovieDtoBean>> {
        return mockDataSource.getTvShows()
    }

    override fun getPopularMovies(): Single<List<MovieDtoBean>> {
        return mockDataSource.getPopularMovies()
    }

}