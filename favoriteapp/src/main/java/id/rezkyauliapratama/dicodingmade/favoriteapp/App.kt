package id.rezkyauliapratama.dicodingmade.favoriteapp

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import id.innovation.libcore.di.*
import id.innovation.libnetwork.di.NetworkModule
import timber.log.Timber
import id.innovation.libcore.data.locale.LocaleManager
import id.innovation.libcore.di.helper.CoreComponentProvider
import id.innovation.libcore.di.module.CoreModule
import id.innovation.libcore.di.module.SchedulersModule


class App : Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        provideCoreComponent().inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.setLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {

            coreComponent = DaggerCoreComponent.builder()
                .coreModule(CoreModule(this))
                .schedulerModule(SchedulersModule())
                .networkModule(NetworkModule(BuildConfig.API_BASE_URL, arrayListOf()))
                .databaseModule(DatabaseModule("database"))
                .build()
        }
        return coreComponent
    }
}