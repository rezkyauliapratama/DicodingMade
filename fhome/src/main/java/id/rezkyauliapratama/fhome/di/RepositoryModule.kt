package id.rezkyauliapratama.fhome.di

import android.content.Context
import dagger.Module
import dagger.Provides
import id.innovation.innovendor.fsample.data.sample.MovieApi
import id.innovation.libcore.di.ActivityContext
import id.innovation.libcore.di.FeatureScope
import id.innovation.libnetwork.services
import id.rezkyauliapratama.fhome.data.DataManager
import id.rezkyauliapratama.fhome.data.MovieRepositoryImpl
import id.rezkyauliapratama.fhome.data.source.DataManagerImpl
import id.rezkyauliapratama.fhome.data.source.api.MovieApiDataSource
import id.rezkyauliapratama.fhome.data.source.api.MovieApiDataSourceImpl
import id.rezkyauliapratama.fhome.data.source.mock.MovieMockDataSource
import id.rezkyauliapratama.fhome.data.source.mock.MovieMockDataSourceImpl
import id.rezkyauliapratama.fhome.domain.repository.MovieRepository
import retrofit2.Retrofit

@Module
class RepositoryModule {

    @Provides
    @FeatureScope
    fun provideApi(retrofit: Retrofit): MovieApi {
        return services(retrofit)
    }

    @Provides
    fun provideMockSource(@ActivityContext context: Context): MovieMockDataSource {
        return MovieMockDataSourceImpl(context)
    }

    @Provides
    fun provideApiSource(api: MovieApi): MovieApiDataSource {
        return MovieApiDataSourceImpl(api)
    }

    @Provides
    fun provideDataManager(
        mockDataSource: MovieMockDataSource,
        apiDataSource: MovieApiDataSource
    ): DataManager {
        return DataManagerImpl(mockDataSource, apiDataSource)
    }

    @Provides
    fun provideRepository(
        dataManager: DataManager
    ): MovieRepository {
        return MovieRepositoryImpl(dataManager)
    }
}