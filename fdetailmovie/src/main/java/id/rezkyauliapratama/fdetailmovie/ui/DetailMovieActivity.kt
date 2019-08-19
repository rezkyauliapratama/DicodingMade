package id.rezkyauliapratama.fdetailmovie.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import id.dicodingmade.fdetailmovie.R
import id.innovation.libcore.di.CoreInjectHelper
import id.innovation.libcore.di.PresenterModule
import id.innovation.libcore.ui.controllers.BaseViewModelActivity
import id.innovation.libnavigation.Activities
import id.innovation.libsharedata.entity.DetailMovieResult
import id.rezkyauliapratama.fdetailmovie.di.DaggerFeatureComponent

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
        val detailMovieResult = intent?.getParcelableExtra<DetailMovieResult>(Activities.DetailMovie.bundleKey)

        detailMovieResult?.apply {
            viewModel.getDetailMovieResult(this)
        }
    }

}
