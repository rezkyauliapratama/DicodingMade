package id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.favorite.tvshow

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
import id.rezkyauliapratama.dicodingmade.favoriteapp.R
import id.rezkyauliapratama.dicodingmade.favoriteapp.di.DaggerFeatureComponent
import id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.FavoriteViewModel
import id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.entity.TvShowResult
import id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.favorite.tvshow.adapter.FavoriteTvShowAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

class FavoriteTvShowFragment : BaseViewModelFragment<FavoriteViewModel>() {

    @Inject
    lateinit var adapter: FavoriteTvShowAdapter


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
        viewModel.tvShowFavoritesLiveData.observe(viewLifecycleOwner, SafeObserver(::handleSuccess))
    }

    private fun handleSuccess(resource: Resource<List<TvShowResult>>) {
        when (resource.state) {
            ResourceState.LOADING -> {
                swipeRefreshLayout.isRefreshing = true
            }
            ResourceState.SUCCESS -> {
                swipeRefreshLayout.isRefreshing = false
                resource.data?.apply { adapter.setItems(this) }
            }
            ResourceState.ERROR -> {
                swipeRefreshLayout.isRefreshing = false
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

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getTvShowFavorite()
        }
    }

    private fun onItemClick(tvShowId: Int) {
        val intent = intentTo(
            requireContext(),
            addressableActivity = Activities.DetailMovie
        )

        intent.putExtra(Activities.DetailMovie.bundleFirstKey, tvShowId)
        intent.putExtra(
            Activities.DetailMovie.bundleSecondKey,
            Activities.DetailMovie.DetailType.TV
        )
        startActivity(intent)
    }


}