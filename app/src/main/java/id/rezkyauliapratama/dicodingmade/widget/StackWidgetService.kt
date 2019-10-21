package id.rezkyauliapratama.dicodingmade.widget

import android.content.Intent
import android.widget.RemoteViewsService
import id.innovation.libcore.di.helper.CoreInjectHelper
import id.rezkyauliapratama.dicodingmade.di.DaggerFeatureComponent
import id.rezkyauliapratama.dicodingmade.domain.repository.MovieRepository
import id.rezkyauliapratama.dicodingmade.domain.usecase.MovieFavoritesUseCase
import javax.inject.Inject

class StackWidgetService : RemoteViewsService() {

    @Inject
    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        super.onCreate()
        DaggerFeatureComponent
            .builder()
            .coreComponent(
                CoreInjectHelper.provideCoreComponent(
                    this.applicationContext
                )
            )
            .build()
            .inject(this)
    }

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory =
        StackRemoteViewsFactory(this.applicationContext, movieRepository)
}