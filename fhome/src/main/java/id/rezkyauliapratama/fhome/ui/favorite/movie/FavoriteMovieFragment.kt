package id.rezkyauliapratama.fhome.ui.favorite.movie

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import id.innovation.libcore.di.helper.CoreInjectHelper.provideCoreComponent
import id.innovation.libcore.di.module.PresenterModule
import id.innovation.libcore.ui.common.SafeObserver
import id.innovation.libcore.ui.controllers.BaseViewModelFragment
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.presenterstate.ResourceState
import id.innovation.libnavigation.Activities
import id.innovation.libnavigation.intentTo
import id.innovation.libuicomponent.common.ProgressDialogUtil
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.fhome.di.DaggerFeatureComponent
import id.rezkyauliapratama.dicodingmade.presenter.entity.PopularMovieResult
import id.rezkyauliapratama.dicodingmade.presenter.entity.TvShowResult
import id.rezkyauliapratama.fhome.ui.favorite.FavoriteViewModel
import id.rezkyauliapratama.fhome.ui.favorite.movie.adapter.FavoriteMovieAdapter
import id.rezkyauliapratama.fhome.ui.favorite.tvshow.adapter.FavoriteTvShowAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*
import java.lang.ref.WeakReference
import javax.inject.Inject

class FavoriteMovieFragment : BaseViewModelFragment<FavoriteViewModel>() {

    @Inject
    lateinit var adapter: FavoriteMovieAdapter


    override fun buildViewModel(): FavoriteViewModel {
        return ViewModelProviders.of(
            requireActivity(),
            mViewModelFactory
        )[FavoriteViewModel::class.java]
    }

    override fun getContentResource(): Int {
        return R.layout.fragment_movie_list
    }

    override fun injectDagger() {
        DaggerFeatureComponent
            .builder()
            .coreComponent(provideCoreComponent(requireActivity().applicationContext))
            .presenterModule(PresenterModule(requireActivity()))
            .build()
            .inject(this)
    }


    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        viewModel.movieFavoritesLiveData.observe(viewLifecycleOwner, SafeObserver(::handleSuccess))
    }

    private fun handleSuccess(resource: Resource<List<PopularMovieResult>>) {
        when (resource.state) {
            ResourceState.LOADING -> {
                ProgressDialogUtil.showProgressDialog(
                    WeakReference(requireContext())
                )
            }
            ResourceState.SUCCESS -> {
                ProgressDialogUtil.hideProgressDialog()
                resource.data?.apply { adapter.setItems(this) }
            }
            ResourceState.ERROR -> {
                ProgressDialogUtil.hideProgressDialog()
                resource.throwable?.apply { handleGenericError(this) }
            }
        }
    }


    override fun initViews() {
        super.initViews()
        val layoutManager = GridLayoutManager(requireContext(), 2)
        rvPopularMovies.layoutManager = layoutManager
        rvPopularMovies.adapter = adapter

        adapter.setOnClick(::onItemClick)
    }

    private fun onItemClick(tvShowId: Int) {
        val intent = intentTo(
            requireContext(),
            addressableActivity = Activities.DetailMovie
        )

        intent.putExtra(Activities.DetailMovie.bundleFirstKey, tvShowId)
        intent.putExtra(
            Activities.DetailMovie.bundleSecondKey,
            Activities.DetailMovie.DetailType.MOVIE
        )
        startActivity(intent)
    }


}