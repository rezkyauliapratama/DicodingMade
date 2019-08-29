package id.rezkyauliapratama.fhome.ui.popularmovie

import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import id.innovation.libcore.di.CoreInjectHelper.provideCoreComponent
import id.innovation.libcore.di.PresenterModule
import id.innovation.libcore.ui.common.SafeObserver
import id.innovation.libcore.ui.controllers.BaseViewModelFragment
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.presenterstate.ResourceState
import id.innovation.libuicomponent.common.ProgressDialogUtil
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.fhome.di.DaggerFeatureComponent
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import id.rezkyauliapratama.fhome.ui.popularmovie.adapter.PopularMovieAdapter
import id.rezkyauliapratama.fhome.ui.popularmovie.viewmodel.PopularMovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import timber.log.Timber
import java.lang.ref.WeakReference
import javax.inject.Inject

class PopularMovieFragment : BaseViewModelFragment<PopularMovieViewModel>() {

    @Inject
    lateinit var adapter: PopularMovieAdapter


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
    }


    override fun initViews() {
        super.initViews()
        val layoutManager: GridLayoutManager = GridLayoutManager(requireContext(), 2)
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
    }

    private fun onItemClick(popularMovieResult: PopularMovieResult) {

    }

    private fun handleStateResult(resourceState: Resource<List<PopularMovieResult>>) {
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
                Timber.e("error : ${resourceState.throwable}")
            }
        }
    }

    private fun handleMoviesResult(pagedList: PagedList<PopularMovieResult>) {
        adapter.submitList(pagedList)
    }
}