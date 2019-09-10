package id.rezkyauliapratama.fdetailmovie.di

import android.content.Context
import dagger.Module
import dagger.Provides
import id.innovation.libcore.di.annotation.ActivityContext
import id.innovation.libcore.di.annotation.FeatureScope
import id.innovation.libdatabase.dao.FavoriteDao
import id.innovation.libnetwork.services
import id.rezkyauliapratama.fdetailmovie.data.DataManager
import id.rezkyauliapratama.fdetailmovie.data.DetailContentRepositoryImpl
import id.rezkyauliapratama.fdetailmovie.data.api.DetailMovieApi
import id.rezkyauliapratama.fdetailmovie.data.api.DetailTvShowApi
import id.rezkyauliapratama.fdetailmovie.data.source.DataManagerImpl
import id.rezkyauliapratama.fdetailmovie.data.source.api.DetailContentApiDataSource
import id.rezkyauliapratama.fdetailmovie.data.source.api.DetailContentApiDataSourceImpl
import id.rezkyauliapratama.fdetailmovie.data.source.local.DetailContentLocalDataSource
import id.rezkyauliapratama.fdetailmovie.data.source.local.DetailContentLocalDataSourceImpl
import id.rezkyauliapratama.fdetailmovie.domain.repository.DetailContentRepository
import retrofit2.Retrofit


@Module
class RepositoryModule {

    @Provides
    @FeatureScope
    fun provideDetailMovieApi(retrofit: Retrofit): DetailMovieApi {
        return services(retrofit)
    }

    @Provides
    @FeatureScope
    fun provideDetailTvApi(retrofit: Retrofit): DetailTvShowApi {
        return services(retrofit)
    }

    @Provides
    fun provideApiSource(
        detailMovieApi: DetailMovieApi,
        detailTvShowApi: DetailTvShowApi
    ): DetailContentApiDataSource {
        return DetailContentApiDataSourceImpl(detailMovieApi, detailTvShowApi)
    }

    @Provides
    fun provideLocalSource(favoriteDao: FavoriteDao): DetailContentLocalDataSource {
        return DetailContentLocalDataSourceImpl(favoriteDao)
    }

    @Provides
    fun provideDataManager(
        apiDataSource: DetailContentApiDataSource,
        localDataSource: DetailContentLocalDataSource
    ): DataManager {
        return DataManagerImpl(apiDataSource, localDataSource)
    }

    @Provides
    fun provideRepository(
        @ActivityContext context: Context,
        dataManager: DataManager
    ): DetailContentRepository {
        return DetailContentRepositoryImpl(context, dataManager)
    }
}