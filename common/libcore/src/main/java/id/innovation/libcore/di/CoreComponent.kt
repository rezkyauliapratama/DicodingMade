package id.innovation.libcore.di

import android.app.Application
import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Component
import id.innovation.libcore.data.executors.SharedPref
import id.innovation.libcore.di.annotation.ApplicationContext
import id.innovation.libcore.di.module.CoreModule
import id.innovation.libcore.di.module.SchedulersModule
import id.innovation.libcore.domain.executors.PostExecutionThread
import id.innovation.libcore.domain.executors.ThreadExecutor
import id.innovation.libdatabase.dao.FavoriteDao
import id.innovation.libnetwork.common.NetworkErrorInterface
import id.innovation.libnetwork.di.NetworkModule
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [SchedulersModule::class, CoreModule::class, NetworkModule::class, DatabaseModule::class])
@Singleton
interface CoreComponent {
    @ApplicationContext
    fun getApplicationContext(): Context

    fun getBaseResponse(): NetworkErrorInterface
    fun getThreadExecutor(): ThreadExecutor
    fun getPostExecutionThread(): PostExecutionThread
    fun getRetrofit(): Retrofit
    fun getMoshi(): Moshi
    fun getFavoriteDao(): FavoriteDao
    fun getSharedPref(): SharedPref
    fun inject(app: Application)

    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
        fun coreModule(module: CoreModule): Builder
        fun schedulerModule(module: SchedulersModule): Builder
        fun networkModule(module: NetworkModule): Builder
        fun databaseModule(module: DatabaseModule): Builder
    }

}
