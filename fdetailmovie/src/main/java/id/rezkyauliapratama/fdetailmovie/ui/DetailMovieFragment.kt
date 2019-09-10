package id.rezkyauliapratama.fdetailmovie.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import id.dicodingmade.fdetailmovie.R
import id.innovation.libuicomponent.R as R2
import id.innovation.libcore.ui.common.SafeObserver
import id.innovation.libcore.ui.controllers.BaseFragment
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.presenterstate.ResourceState
import id.innovation.libsharedata.entity.DetailMovieResult
import id.innovation.libuicomponent.common.ProgressDialogUtil
import id.innovation.libuicomponent.common.extension.loadImage
import id.rezkyauliapratama.dicodingmade.BuildConfig
import id.rezkyauliapratama.fdetailmovie.di.DaggerFeatureComponent
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import timber.log.Timber
import java.lang.ref.WeakReference
import id.innovation.libcore.di.helper.CoreInjectHelper
import id.innovation.libcore.di.module.PresenterModule

class DetailMovieFragment : BaseFragment() {

    private fun sharedViweModel(): DetailMovieViewModel {
        return ViewModelProviders.of(
            requireActivity(),
            mViewModelFactory
        )[DetailMovieViewModel::class.java]

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

        sharedViweModel().isFavoriteLiveData.observe(
            viewLifecycleOwner,SafeObserver(this::handleFavorite)
        )
    }

    private fun handleFavorite(isFavorite: Boolean) {
        if (isFavorite){
            ivFavorite.setImageResource(R2.drawable.ic_favorite_24dp)
        } else {
            ivFavorite.setImageResource(R2.drawable.ic_favorite_border_24dp)
        }
    }

    private fun handleDetailMovieResult(resource: Resource<DetailMovieResult>) {
        when (resource.state) {
            is ResourceState.LOADING -> ProgressDialogUtil.showProgressDialog(
                WeakReference(
                    requireContext()
                )
            )
            is ResourceState.SUCCESS -> {
                ProgressDialogUtil.hideProgressDialog()
                resource.data?.apply { setData(this) }
            }
            is ResourceState.ERROR -> {
                ProgressDialogUtil.hideProgressDialog()
                resource.throwable?.apply {
                    Timber.e("error : ${this}")
                    handleGenericError(this)
                }
            }
        }
    }

    private fun setData(detailMovieResult: DetailMovieResult) {
        tvTitle.text = detailMovieResult.title
        tvValueOverview.text = detailMovieResult.overview
        tvScore.text = detailMovieResult.popularity.toString()
        ivPoster.loadImage(StringBuilder().append(BuildConfig.IMAGE_BASE_URL)
            .append(detailMovieResult.backdropPath).toString(),
            onLoad = {

            },
            onSuccess = {

            }
        )
    }
}