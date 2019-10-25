package id.rezkyauliapratama.dicodingmade.favoriteapp

import id.innovation.libcore.di.helper.CoreInjectHelper
import id.innovation.libcore.di.module.PresenterModule
import id.innovation.libcore.ui.controllers.BaseActivity
import id.rezkyauliapratama.dicodingmade.favoriteapp.di.DaggerFeatureComponent

class MainActivity : BaseActivity() {

    override fun injectDagger() {
        DaggerFeatureComponent
            .builder()
            .coreComponent(
                CoreInjectHelper.provideCoreComponent(
                    applicationContext
                )
            )
            .presenterModule(PresenterModule(this))
            .build()
            .inject(this)
    }

    override fun getContentResource(): Int {
        return R.layout.activity_main
    }


}
