package id.rezkyauliapratama.fdetailmovie.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import id.dicodingmade.fdetailmovie.R
import id.innovation.libcore.di.CoreInjectHelper
import id.innovation.libcore.di.PresenterModule
import id.innovation.libcore.ui.common.SafeObserver
import id.innovation.libcore.ui.controllers.BaseFragment
import id.innovation.libsharedata.entity.DetailMovieResult
import id.rezkyauliapratama.fdetailmovie.di.DaggerFeatureComponent
import kotlinx.android.synthetic.main.fragment_detail_movie.*

class DetailMovieFragment : BaseFragment() {

    private fun sharedViweModel(): DetailMovieViewModel {
        return ViewModelProviders.of(requireActivity(), mViewModelFactory)[DetailMovieViewModel::class.java]

    }

    override fun getContentResource(): Int {
        return R.layout.fragment_detail_movie
    }

    override fun injectDagger() {
        DaggerFeatureComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireActivity().applicationContext))
            .presenterModule(PresenterModule(requireActivity()))
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViweModel().detailMovieLiveData.observe(
            viewLifecycleOwner,
            SafeObserver(this::handleDetailMovieResult)
        )
    }

    private fun handleDetailMovieResult(detailMovieResult: DetailMovieResult) {
        tvTitle.text = detailMovieResult.title
        tvValueOverview.text = detailMovieResult.overview
        tvScore.text = detailMovieResult.popularity.toString()
        ivPoster.setImageResource(detailMovieResult.posterPath)
    }
}