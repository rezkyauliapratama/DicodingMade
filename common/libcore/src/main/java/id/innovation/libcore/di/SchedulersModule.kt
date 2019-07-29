package id.innovation.libcore.di

import dagger.Module
import dagger.Provides
import id.innovation.libcore.data.executors.JobExecutor
import id.innovation.libcore.domain.executors.PostExecutionThread
import id.innovation.libcore.domain.executors.ThreadExecutor
import id.innovation.libcore.ui.UIThread

@Module
class SchedulersModule {

    @Provides
    fun providesThreadExecutor(): ThreadExecutor {
        return JobExecutor()
    }

    @Provides
    fun provides(): PostExecutionThread {
        return UIThread()
    }
}
