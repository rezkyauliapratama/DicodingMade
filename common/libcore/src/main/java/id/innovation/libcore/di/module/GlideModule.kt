package id.innovation.libcore.di.module

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import id.innovation.libcore.di.annotation.ActivityContext


@Module
class GlideModule {

    @Provides
    fun providesGlide(@ActivityContext context: Context): RequestManager {
        return Glide.with(context)
    }

}
