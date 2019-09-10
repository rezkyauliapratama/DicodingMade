package id.innovation.libcore.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import id.innovation.libcore.baseapi.BaseResponse
import id.innovation.libcore.di.annotation.ApplicationContext
import id.innovation.libnetwork.common.NetworkErrorInterface

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
}
