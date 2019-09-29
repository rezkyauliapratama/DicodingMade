package id.rezkyauliapratama.fhome.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import id.innovation.libcore.di.helper.CoreInjectHelper.provideCoreComponent
import id.innovation.libcore.di.module.PresenterModule
import id.innovation.libcore.ui.common.SafeObserver
import id.innovation.libcore.ui.controllers.BaseViewModelFragment
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.fhome.di.DaggerFeatureComponent
import id.rezkyauliapratama.fhome.ui.bottomsheet.SettingBottomSheetDialog
import id.rezkyauliapratama.fhome.ui.favorite.FavoriteFragment
import id.rezkyauliapratama.fhome.ui.pager.HomePagerAdapter
import id.rezkyauliapratama.fhome.ui.popularmovie.PopularMovieFragment
import id.rezkyauliapratama.fhome.ui.tvshow.TvShowFragment
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber
import id.innovation.libuicomponent.R as R2

class HomeFragment : BaseViewModelFragment<HomeViewModel>() {

    private var selectedFragment: Fragment? = null


    override fun buildViewModel(): HomeViewModel {
        return ViewModelProviders.of(
            requireActivity(),
            mViewModelFactory
        )[HomeViewModel::class.java]
    }

    private val settingDialogBottomSheet by lazy {
        SettingBottomSheetDialog()
    }

    private val homePagerAdapter by lazy {
        HomePagerAdapter(childFragmentManager).apply {
            addFragment(PopularMovieFragment(), getString(R2.string.home_tab_movie))
            addFragment(TvShowFragment(), getString(R2.string.home_tab_tv_show))
            addFragment(FavoriteFragment(), getString(R2.string.home_tab_favorite))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun getContentResource(): Int {
        return R.layout.fragment_home
    }

    override fun injectDagger() {
        DaggerFeatureComponent
            .builder()
            .coreComponent(provideCoreComponent(requireActivity().applicationContext))
            .presenterModule(PresenterModule(requireActivity()))
            .build()
            .inject(this)
    }

    override fun initViews() {
        super.initViews()
        vpContainer.adapter = homePagerAdapter
        vpContainer.offscreenPageLimit = homePagerAdapter.count
        tabLayout.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_tab_movie -> {
                    vpContainer.setCurrentItem(0, false)
                    setSearchView(0)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_tab_tv -> {
                    vpContainer.setCurrentItem(1, false)
                    setSearchView(1)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_tab_favorite -> {
                    vpContainer.setCurrentItem(2, false)
                    setSearchView(2)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

        fabSetting.setOnClickListener {
            showDialogFragment(
                settingDialogBottomSheet,
                HomeActivity::class.java.simpleName.toString()
            )
        }
    }

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        viewModel.queryLiveData.observe(viewLifecycleOwner, SafeObserver(::queryResult))
    }

    private fun queryResult(query: String) {
        if (selectedFragment is PopularMovieFragment) {
            viewModel.setSearchMovie(query)
        } else if (selectedFragment is TvShowFragment) {
            viewModel.setSearchTvShow(query)
        }
    }

    private fun setSearchView(position: Int) {
        selectedFragment = homePagerAdapter.getItem(position)
        when (position) {
            1 -> {
                viewModel.setSearchView(View.VISIBLE)
            }
            2 -> {
                viewModel.setSearchView(View.VISIBLE)
            }
            3 -> {
                viewModel.setSearchView(View.GONE)
            }
        }
    }


}