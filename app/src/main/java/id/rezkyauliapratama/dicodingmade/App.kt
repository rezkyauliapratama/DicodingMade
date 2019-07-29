package id.rezkyauliapratama.dicodingmade

import android.app.Application
import id.innovation.libcore.di.*
import id.innovation.libnetwork.di.NetworkModule
import timber.log.Timber


class App : Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        provideCoreComponent().inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {

            coreComponent = DaggerCoreComponent.builder()
                .coreModule(CoreModule())
                .schedulerModule(SchedulersModule())
                .networkModule(NetworkModule(BuildConfig.API_BASE_URL, arrayListOf()))
                .build()
        }
        return coreComponent
    }
}
