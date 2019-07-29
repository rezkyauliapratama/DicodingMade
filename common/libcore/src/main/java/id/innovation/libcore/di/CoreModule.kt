package id.innovation.libcore.di

import dagger.Module
import dagger.Provides
import id.innovation.libcore.baseapi.BaseResponse
import id.innovation.libnetwork.common.NetworkErrorInterface

@Module
class CoreModule {

    @Provides
    fun provideBaseApiResponse(): NetworkErrorInterface {
        return BaseResponse()
    }
}
