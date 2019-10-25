package id.rezkyauliapratama.dicodingmade.favoriteapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import id.innovation.libcore.di.annotation.ActivityContext
import id.innovation.libcore.di.annotation.ApplicationContext
import id.innovation.libcore.di.annotation.FeatureScope
import id.innovation.libnetwork.services
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.DataManager
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.MovieRepositoryImpl
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.api.DetailMovieApi
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.api.DetailTvShowApi
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.source.DataManagerImpl
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.source.api.MovieApiDataSource
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.source.api.MovieApiDataSourceImpl
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.source.local.MovieLocalDataSource
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.source.local.MovieLocalDataSourceImpl
import id.rezkyauliapratama.dicodingmade.favoriteapp.domain.repository.MovieRepository
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
    fun provideDetailTvShowApi(retrofit: Retrofit): DetailTvShowApi {
        return services(retrofit)
    }

    @Provides
    fun provideApiSource(
        detailMovieApi: DetailMovieApi,
        detailTvShowApi: DetailTvShowApi
    ): MovieApiDataSource {
        return MovieApiDataSourceImpl(detailMovieApi, detailTvShowApi)
    }

    @Provides
    fun provideLocalSource(@ActivityContext context: Context): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(context)
    }


    @Provides
    fun provideDataManager(
        apiDataSource: MovieApiDataSource,
        localDataSource: MovieLocalDataSource
    ): DataManager {
        return DataManagerImpl(apiDataSource, localDataSource)
    }

    @Provides
    fun provideRepository(
        @ApplicationContext context: Context,
        dataManager: DataManager
    ): MovieRepository {
        return MovieRepositoryImpl(context, dataManager)
    }
}