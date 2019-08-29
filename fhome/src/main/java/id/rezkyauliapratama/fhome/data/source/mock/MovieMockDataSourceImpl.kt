package id.rezkyauliapratama.fhome.data.source.mock

import android.content.Context
import id.rezkyauliapratama.fhome.data.entity.TvShowDtoBean
import id.rezkyauliapratama.fhome.data.mock.TvShowMockDataFactory
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class MovieMockDataSourceImpl(private val context:Context) : MovieMockDataSource {

    override fun getTvShows(): Single<List<TvShowDtoBean>> = Single.just(
        TvShowMockDataFactory.create(context)
    ).map {
        it.tvShowsDto
    }.delay(3, TimeUnit.SECONDS)

}