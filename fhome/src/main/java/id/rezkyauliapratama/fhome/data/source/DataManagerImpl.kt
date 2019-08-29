package id.rezkyauliapratama.fhome.data.source

import id.rezkyauliapratama.fhome.data.DataManager
import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import id.rezkyauliapratama.fhome.data.entity.TvShowDtoBean
import id.rezkyauliapratama.fhome.data.source.api.MovieApiDataSource
import id.rezkyauliapratama.fhome.data.source.mock.MovieMockDataSource
import io.reactivex.Single

class DataManagerImpl(
    private val mockDataSource: MovieMockDataSource,
    private val apiDataSource: MovieApiDataSource
) : DataManager {

    override fun getPopularMovies(apiKey: String, pageNum: Int): Single<List<MovieDtoBean>> =
        apiDataSource.getPopularMovies(apiKey, pageNum)


    override fun getTvShows(): Single<List<TvShowDtoBean>> {
        return mockDataSource.getTvShows()
    }

}