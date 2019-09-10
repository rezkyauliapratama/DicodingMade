package id.innovation.libcore.di.module

import android.content.Context
import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import id.innovation.libcore.di.annotation.ActivityContext


@Module
class PresenterModule(private val activity: FragmentActivity) {

    @Provides
    @ActivityContext
    fun provideActivityContext(): Context = activity

    @Provides
    fun provideActivity(): FragmentActivity = activity

}
