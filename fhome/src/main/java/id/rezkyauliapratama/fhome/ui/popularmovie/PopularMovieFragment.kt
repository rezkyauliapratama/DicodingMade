package id.rezkyauliapratama.fhome.ui.popularmovie

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.innovation.libcore.di.CoreInjectHelper.provideCoreComponent
import id.innovation.libcore.di.PresenterModule
import id.innovation.libcore.ui.common.SafeObserver
import id.innovation.libcore.ui.controllers.BaseViewModelFragment
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.presenterstate.ResourceState
import id.innovation.libnavigation.Activities
import id.innovation.libnavigation.intentTo
import id.innovation.libuicomponent.common.ProgressDialogUtil
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.fhome.di.DaggerFeatureComponent
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import id.rezkyauliapratama.fhome.ui.entity.intoDetailMovie
import id.rezkyauliapratama.fhome.ui.popularmovie.adapter.PopularMovieAdapter
import id.rezkyauliapratama.fhome.ui.popularmovie.viewmodel.PopularMovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import timber.log.Timber
import java.lang.ref.WeakReference

class PopularMovieFragment : BaseViewModelFragment<PopularMovieViewModel>() {

    private lateinit var adapter: PopularMovieAdapter

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
            SafeObserver(this::handleStateResult)
        )
    }

    override fun initViews() {
        super.initViews()
        adapter = PopularMovieAdapter(::onItemClick)
        rvPopularMovies.adapter = adapter
        rvPopularMovies.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onItemClick(popularMovieResult: PopularMovieResult) {
        val intent = intentTo(
            requireContext(),
            addressableActivity = Activities.DetailMovie
        )

        intent.putExtra(Activities.DetailMovie.bundleKey, popularMovieResult.intoDetailMovie())

        startActivity(
            intent
        )
    }

    private fun handleStateResult(resource: Resource<List<PopularMovieResult>>) {
        when (resource.state) {
            ResourceState.LOADING -> {
                ProgressDialogUtil.showProgressDialog(WeakReference(requireContext()))
            }

            ResourceState.SUCCESS -> ProgressDialogUtil.hideProgressDialog()

            ResourceState.ERROR -> {
                ProgressDialogUtil.hideProgressDialog()
            }
        }
        resource.data?.apply {
            adapter.bind(this)
        }
    }
}