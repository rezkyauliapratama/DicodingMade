package id.rezkyauliapratama.fhome.di

import dagger.Module
import dagger.Provides
import id.rezkyauliapratama.fhome.data.DataManager
import id.rezkyauliapratama.fhome.data.MovieRepositoryImpl
import id.rezkyauliapratama.fhome.data.source.DataManagerImpl
import id.rezkyauliapratama.fhome.data.source.mock.MovieMockDataSource
import id.rezkyauliapratama.fhome.data.source.mock.MovieMockDataSourceImpl
import id.rezkyauliapratama.fhome.domain.repository.MovieRepository

@Module
class RepositoryModule {

    @Provides
    fun provideMockSource(): MovieMockDataSource{
        return MovieMockDataSourceImpl()
    }

    @Provides
    fun provideDataManager(
        mockDataSource: MovieMockDataSource
    ): DataManager {
        return DataManagerImpl(mockDataSource)
    }

    @Provides
    fun provideRepository(
        dataManager: DataManager
    ): MovieRepository {
        return MovieRepositoryImpl(dataManager)
    }
}