package id.rezkyauliapratama.fhome.ui.tvshow

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
import id.rezkyauliapratama.fhome.ui.entity.TvShowResult
import id.rezkyauliapratama.fhome.ui.tvshow.adapter.TvShowAdapter
import id.rezkyauliapratama.fhome.ui.tvshow.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import timber.log.Timber
import java.lang.ref.WeakReference
import javax.inject.Inject

class TvShowFragment : BaseViewModelFragment<TvShowViewModel>() {

    @Inject
    lateinit var adapter: TvShowAdapter


    override fun buildViewModel(): TvShowViewModel {
        return ViewModelProviders.of(this, mViewModelFactory)[TvShowViewModel::class.java]
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

        viewModel.tvShowList.observe(
            viewLifecycleOwner,
            SafeObserver(this::handleTvShowResult)
        )
        viewModel.getState().observe(
            viewLifecycleOwner,
            SafeObserver(this::handleStateResult)
        )
    }


    override fun initViews() {
        super.initViews()
        val layoutManager = GridLayoutManager(requireContext(), 2)
        val lookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    TvShowAdapter.DATA_VIEW_TYPE -> 1
                    else -> 2
                }
            }
        }

        layoutManager.spanSizeLookup = lookup

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
            Activities.DetailMovie.DetailType.TV
        )
        startActivity(intent)
    }

    private fun handleStateResult(resourceState: Resource<List<TvShowResult>>) {
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

    private fun handleTvShowResult(pagedList: PagedList<TvShowResult>) {
        adapter.submitList(pagedList)
    }
}