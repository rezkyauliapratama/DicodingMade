package id.rezkyauliapratama.fhome.ui

import androidx.fragment.app.Fragment
import id.innovation.libcore.di.helper.CoreInjectHelper.provideCoreComponent
import id.innovation.libcore.ui.controllers.BaseFragment
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.fhome.di.DaggerFeatureComponent
import id.rezkyauliapratama.fhome.ui.bottomsheet.SettingBottomSheetDialog
import id.rezkyauliapratama.fhome.ui.pager.HomePagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar.*
import id.innovation.libuicomponent.R as R2
import id.innovation.libcore.di.module.PresenterModule
import id.rezkyauliapratama.fhome.ui.popularmovie.PopularMovieFragment
import id.rezkyauliapratama.fhome.ui.tvshow.TvShowFragment
import id.rezkyauliapratama.fhome.ui.favorite.FavoriteFragment

class HomeFragment : BaseFragment() {

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
        tvTitle.setText(R2.string.home)
        vpContainer.adapter = homePagerAdapter
        vpContainer.offscreenPageLimit = homePagerAdapter.count
        tabLayout.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_tab_movie -> {
                    vpContainer.setCurrentItem(0, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_tab_tv -> {
                    vpContainer.setCurrentItem(1, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_tab_favorite -> {
                    vpContainer.setCurrentItem(2, false)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
        fabSetting.setOnClickListener {
            showDialogFragment(settingDialogBottomSheet, HomeActivity::class.java.simpleName.toString())
        }
    }


}