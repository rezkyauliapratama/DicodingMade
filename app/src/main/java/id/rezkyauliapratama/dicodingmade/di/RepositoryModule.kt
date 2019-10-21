package id.rezkyauliapratama.dicodingmade.di

import android.content.Context
import dagger.Module
import dagger.Provides
import id.innovation.libcore.di.annotation.ActivityContext
import id.innovation.libcore.di.annotation.ApplicationContext
import id.innovation.libcore.di.annotation.FeatureScope
import id.innovation.libdatabase.dao.FavoriteDao
import id.innovation.libnetwork.services
import id.rezkyauliapratama.dicodingmade.data.DataManager
import id.rezkyauliapratama.dicodingmade.data.MovieRepositoryImpl
import id.rezkyauliapratama.dicodingmade.data.api.DetailMovieApi
import id.rezkyauliapratama.dicodingmade.data.api.DetailTvShowApi
import id.rezkyauliapratama.dicodingmade.data.api.MovieApi
import id.rezkyauliapratama.dicodingmade.data.source.DataManagerImpl
import id.rezkyauliapratama.dicodingmade.data.source.api.MovieApiDataSource
import id.rezkyauliapratama.dicodingmade.data.source.api.MovieApiDataSourceImpl
import id.rezkyauliapratama.dicodingmade.data.source.local.MovieLocalDataSource
import id.rezkyauliapratama.dicodingmade.data.source.local.MovieLocalDataSourceImpl
import id.rezkyauliapratama.dicodingmade.domain.repository.MovieRepository
import retrofit2.Retrofit

@Module
class RepositoryModule {

    @Provides
    @FeatureScope
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return services(retrofit)
    }

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
        movieApi: MovieApi,
        detailMovieApi: DetailMovieApi,
        detailTvShowApi: DetailTvShowApi
    ): MovieApiDataSource {
        return MovieApiDataSourceImpl(movieApi, detailMovieApi, detailTvShowApi)
    }

    @Provides
    fun provideLocalSource(favoriteDao: FavoriteDao): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(favoriteDao)

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