package id.rezkyauliapratama.fdetailmovie.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import id.dicodingmade.fdetailmovie.R
import id.innovation.libcore.di.CoreInjectHelper
import id.innovation.libcore.di.PresenterModule
import id.innovation.libcore.ui.controllers.BaseViewModelActivity
import id.innovation.libnavigation.Activities
import id.rezkyauliapratama.fdetailmovie.di.DaggerFeatureComponent
import timber.log.Timber

class DetailMovieActivity : BaseViewModelActivity<DetailMovieViewModel>() {

    override fun buildViewModel(): DetailMovieViewModel {
        return ViewModelProviders.of(this, mViewModelFactory)[DetailMovieViewModel::class.java]
    }

    override fun injectDagger() {
        DaggerFeatureComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .presenterModule(PresenterModule(this))
            .build()
            .inject(this)
    }

    override fun getContentResource(): Int {
        return R.layout.activity_detail_movie
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra(Activities.DetailMovie.bundleFirstKey, 0)
        val detailType =
            intent.getSerializableExtra(Activities.DetailMovie.bundleSecondKey) as Activities.DetailMovie.DetailType

        if (id > 0)
            when (detailType) {
                Activities.DetailMovie.DetailType.MOVIE -> {
                    Timber.e("detail type >> movie")
                    viewModel.getDetailMovieResult(id)
                }
                Activities.DetailMovie.DetailType.TV -> {
                    Timber.e("detail type >> tv")
                    viewModel.getDetailTvShowResult(id)
                }

            }

    }

}
