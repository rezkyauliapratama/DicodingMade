package id.rezkyauliapratama.fhome.di

import dagger.Module
import dagger.Provides
import id.innovation.libcore.data.executors.SharedPref
import id.rezkyauliapratama.fhome.data.AlarmRepositoryImpl
import id.rezkyauliapratama.fhome.data.DataManager
import id.rezkyauliapratama.fhome.data.source.DataManagerImpl
import id.rezkyauliapratama.fhome.data.source.local.AlarmLocalDataSource
import id.rezkyauliapratama.fhome.data.source.local.AlarmLocalDataSourceImpl
import id.rezkyauliapratama.fhome.domain.repository.AlarmRepository

@Module
class AlarmRepositoryModule {

    @Provides
    fun provideLocalSource(sharedPref: SharedPref): AlarmLocalDataSource {
        return AlarmLocalDataSourceImpl(sharedPref)
    }

    @Provides
    fun provideDataManager(alarmLocalDataSource: AlarmLocalDataSource): DataManager {
        return DataManagerImpl(alarmLocalDataSource)
    }

    @Provides
    fun provideAlarmRepository(dataManager: DataManager): AlarmRepository {
        return AlarmRepositoryImpl(dataManager)
    }
}