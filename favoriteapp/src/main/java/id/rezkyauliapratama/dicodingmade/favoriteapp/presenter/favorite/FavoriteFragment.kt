package id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.favorite

import androidx.lifecycle.ViewModelProviders
import id.innovation.libcore.di.helper.CoreInjectHelper
import id.innovation.libcore.di.module.PresenterModule
import id.innovation.libcore.ui.controllers.BaseViewModelFragment
import id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.FavoriteViewModel
import id.rezkyauliapratama.fhome.ui.favorite.pager.FavoritePagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import id.rezkyauliapratama.dicodingmade.favoriteapp.R
import id.rezkyauliapratama.dicodingmade.favoriteapp.di.DaggerFeatureComponent

class FavoriteFragment : BaseViewModelFragment<FavoriteViewModel>() {

    override fun buildViewModel(): FavoriteViewModel {
        return ViewModelProviders.of(
            requireActivity(),
            mViewModelFactory
        )[FavoriteViewModel::class.java]
    }

    override fun getContentResource(): Int = R.layout.fragment_favorite

    override fun injectDagger() {
        DaggerFeatureComponent
            .builder()
            .coreComponent(
                CoreInjectHelper.provideCoreComponent(
                    requireActivity().applicationContext
                )
            )
            .presenterModule(PresenterModule(requireActivity()))
            .build()
            .inject(this)
    }

    private val favoritePagerAdapter by lazy {
        FavoritePagerAdapter(requireContext(), childFragmentManager)
    }

    override fun initViews() {
        super.initViews()
        vpContainer.adapter = favoritePagerAdapter
        vpContainer.offscreenPageLimit = favoritePagerAdapter.count
        tabLayout.setupWithViewPager(vpContainer)
    }
}