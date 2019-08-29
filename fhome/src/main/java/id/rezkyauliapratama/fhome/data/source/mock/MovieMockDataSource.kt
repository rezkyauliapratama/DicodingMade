package id.rezkyauliapratama.fhome.data.source.mock

import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import id.rezkyauliapratama.fhome.data.entity.TvShowDtoBean
import io.reactivex.Single


interface MovieMockDataSource {
    fun getTvShows(): Single<List<TvShowDtoBean>>
}