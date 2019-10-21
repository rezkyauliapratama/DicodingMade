package id.rezkyauliapratama.fhome.ui.popularmovie

import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
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
import id.rezkyauliapratama.fhome.ui.HomeViewModel
import id.rezkyauliapratama.dicodingmade.presenter.entity.PopularMovieResult
import id.rezkyauliapratama.fhome.ui.popularmovie.adapter.PopularMovieAdapter
import id.rezkyauliapratama.fhome.ui.popularmovie.viewmodel.PopularMovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import timber.log.Timber
import java.lang.ref.WeakReference
import javax.inject.Inject

class PopularMovieFragment : BaseViewModelFragment<PopularMovieViewModel>() {

    @Inject
    lateinit var adapter: PopularMovieAdapter

    val shareViewModel by lazy {
        ViewModelProviders.of(requireActivity(), mViewModelFactory)[HomeViewModel::class.java]
    }

    override fun buildViewModel(): PopularMovieViewModel {
        return ViewModelProviders.of(this, mViewModelFactory)[PopularMovieViewModel::class.java]
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

        viewModel.moviesList.observe(
            viewLifecycleOwner,
            SafeObserver(this::handleMoviesResult)
        )
        viewModel.getState().observe(
            viewLifecycleOwner,
            SafeObserver(this::handleStateResult)
        )
        shareViewModel.searchMovieLiveData.observe(
            viewLifecycleOwner,
            SafeObserver(this::handleSearchMoviesResult)
        )
    }

    private fun handleSearchMoviesResult(query: String) {
        viewModel.moviesList.removeObservers(viewLifecycleOwner)
        viewModel.getState().removeObservers(viewLifecycleOwner)
        viewModel.setQuery(query)

        viewModel.moviesList.observeForever(SafeObserver(::handleMoviesResult))
        viewModel.getState().observeForever(SafeObserver(::handleStateResult))
    }


    override fun initViews() {
        super.initViews()
        val layoutManager = GridLayoutManager(requireContext(), 2)
        val lookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    PopularMovieAdapter.DATA_VIEW_TYPE -> 1
                    else -> 2
                }
            }
        }

        layoutManager.spanSizeLookup = lookup

        rvPopularMovies.layoutManager = layoutManager
        rvPopularMovies.adapter = adapter

        adapter.setOnClick(::onItemClick)
    }

    private fun onItemClick(movieId: Int) {
        val intent = intentTo(
            requireContext(),
            addressableActivity = Activities.DetailMovie
        )

        intent.putExtra(Activities.DetailMovie.bundleFirstKey, movieId)
        intent.putExtra(
            Activities.DetailMovie.bundleSecondKey,
            Activities.DetailMovie.DetailType.MOVIE
        )
        startActivity(intent)
    }

    private fun handleStateResult(resourceState: Resource<List<PopularMovieResult>>) {
        Timber.e("handleStateResult : ${resourceState.state}")
        if (resourceState.data != null)
        adapter.setResourceState(resourceState.state)

        when (resourceState.state) {
            ResourceState.LOADING -> {
                if (resourceState.data == null) {
                    ProgressDialogUtil.showProgressDialog(WeakReference(requireContext()))
                }
            }
            ResourceState.SUCCESS -> {
                ProgressDialogUtil.hideProgressDialog()
            }
            ResourceState.ERROR -> {
                ProgressDialogUtil.hideProgressDialog()
                resourceState.throwable?.apply {
                    Timber.e("error : ${this}")
                    handleGenericError(this)
                }
            }
        }
    }

    private fun handleMoviesResult(pagedList: PagedList<PopularMovieResult>) {
        Timber.e("handleMovieREsutl")
        adapter.submitList(pagedList)
    }
}