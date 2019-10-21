package id.innovation.libcore.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import id.innovation.libcore.baseapi.BaseResponse
import id.innovation.libcore.data.executors.SharedPref
import id.innovation.libcore.di.annotation.ApplicationContext
import id.innovation.libnetwork.common.NetworkErrorInterface
import javax.inject.Singleton

@Module
class CoreModule(val app: Application) {

    @ApplicationContext
    @Provides
    fun providesApplicationContext(): Context {
        return app.applicationContext
    }

    @Provides
    fun provideBaseApiResponse(): NetworkErrorInterface {
        return BaseResponse()
    }

    @Singleton
    @Provides
    fun provideSharedPref(@ApplicationContext context: Context) : SharedPref {
        return SharedPref(context)
    }
}
