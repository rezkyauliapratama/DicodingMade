package id.rezkyauliapratama.fhome.data.source

import id.rezkyauliapratama.fhome.data.DataManager
import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import id.rezkyauliapratama.fhome.data.entity.TvShowDtoBean
import id.rezkyauliapratama.fhome.data.source.api.MovieApiDataSource
import io.reactivex.Single

class DataManagerImpl(
    private val apiDataSource: MovieApiDataSource
) : DataManager {

    override fun getTvShows(pageNum: Int, language: String): Single<List<TvShowDtoBean>> {
        return apiDataSource.getTvShows(pageNum, language)
    }

    override fun getPopularMovies(pageNum: Int, language: String): Single<List<MovieDtoBean>> =
        apiDataSource.getPopularMovies(pageNum, language)

}