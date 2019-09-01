package id.rezkyauliapratama.fdetailmovie.domain.repository

import id.rezkyauliapratama.fdetailmovie.domain.entity.DetailContentModel
import io.reactivex.Single

interface DetailContentRepository {
    fun getDetailMovie(movieId: Int): Single<DetailContentModel>
    fun getTvShowMovie(tvShowId: Int): Single<DetailContentModel>
}