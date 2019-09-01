package id.rezkyauliapratama.fdetailmovie.data.source

import id.rezkyauliapratama.fdetailmovie.data.DataManager
import id.rezkyauliapratama.fdetailmovie.data.entity.movie.DetailMovieDto
import id.rezkyauliapratama.fdetailmovie.data.entity.tvshow.DetailTvDto
import id.rezkyauliapratama.fdetailmovie.data.source.api.DetailContentApiDataSource
import io.reactivex.Single

class DataManagerImpl(
    private val apiDataSource: DetailContentApiDataSource
) : DataManager {

    override fun getDetailTvShow(tvShowId: Int, language: String): Single<DetailTvDto> {
        return apiDataSource.getDetailTvShow(tvShowId, language)
    }

    override fun getDetailMovie(movieId: Int, language: String): Single<DetailMovieDto> {
        return apiDataSource.getDetailMovie(movieId, language)
    }
}