package id.rezkyauliapratama.fhome.data.source

import id.rezkyauliapratama.fhome.data.DataManager
import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import id.rezkyauliapratama.fhome.data.entity.TvShowDtoBean
import id.rezkyauliapratama.fhome.data.source.api.MovieApiDataSource
import io.reactivex.Single

class DataManagerImpl(
    private val apiDataSource: MovieApiDataSource
) : DataManager {

    override fun getTvShows(apiKey: String, pageNum: Int): Single<List<TvShowDtoBean>> {
        return apiDataSource.getTvShows(apiKey, pageNum)
    }

    override fun getPopularMovies(apiKey: String, pageNum: Int): Single<List<MovieDtoBean>> =
        apiDataSource.getPopularMovies(apiKey, pageNum)

}