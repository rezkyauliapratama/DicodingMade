package id.innovation.libcore.di

import android.app.Application
import com.squareup.moshi.Moshi
import dagger.Component
import id.innovation.libcore.data.locale.LocaleManager
import id.innovation.libcore.domain.executors.PostExecutionThread
import id.innovation.libcore.domain.executors.ThreadExecutor
import id.innovation.libnetwork.common.NetworkErrorInterface
import id.innovation.libnetwork.di.NetworkModule
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [SchedulersModule::class, CoreModule::class, NetworkModule::class])
@Singleton
interface CoreComponent {
    fun getBaseResponse(): NetworkErrorInterface
    fun getThreadExecutor(): ThreadExecutor
    fun getPostExecutionThread(): PostExecutionThread
    fun getRetrofit(): Retrofit
    fun getMoshi(): Moshi

    fun inject(app: Application)

    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
        fun coreModule(module: CoreModule): Builder
        fun schedulerModule(module: SchedulersModule): Builder
        fun networkModule(module: NetworkModule): Builder
    }

}
