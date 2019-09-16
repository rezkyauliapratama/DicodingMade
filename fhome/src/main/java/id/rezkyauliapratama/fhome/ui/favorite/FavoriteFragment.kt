package id.rezkyauliapratama.fhome.ui.favorite

import androidx.lifecycle.ViewModelProviders
import id.innovation.libcore.di.helper.CoreInjectHelper
import id.innovation.libcore.di.module.PresenterModule
import id.innovation.libcore.ui.controllers.BaseViewModelFragment
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.fhome.di.DaggerFeatureComponent

class FavoriteFragment : BaseViewModelFragment<FavoriteViewModel>() {

    override fun buildViewModel(): FavoriteViewModel {
        return ViewModelProviders.of(this)[FavoriteViewModel::class.java]
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



}