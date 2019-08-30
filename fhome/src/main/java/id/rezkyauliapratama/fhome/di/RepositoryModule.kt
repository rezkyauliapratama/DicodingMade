package id.rezkyauliapratama.fhome.di

import dagger.Module
import dagger.Provides
import id.innovation.libcore.di.FeatureScope
import id.innovation.libnetwork.services
import id.rezkyauliapratama.fhome.data.DataManager
import id.rezkyauliapratama.fhome.data.MovieRepositoryImpl
import id.rezkyauliapratama.fhome.data.api.MovieApi
import id.rezkyauliapratama.fhome.data.source.DataManagerImpl
import id.rezkyauliapratama.fhome.data.source.api.MovieApiDataSource
import id.rezkyauliapratama.fhome.data.source.api.MovieApiDataSourceImpl
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
    fun provideApiSource(api: MovieApi): MovieApiDataSource {
        return MovieApiDataSourceImpl(api)
    }

    @Provides
    fun provideDataManager(
        apiDataSource: MovieApiDataSource
    ): DataManager {
        return DataManagerImpl(apiDataSource)
    }

    @Provides
    fun provideRepository(
        dataManager: DataManager
    ): MovieRepository {
        return MovieRepositoryImpl(dataManager)
    }
}