package id.rezkyauliapratama.fhome.ui

import id.innovation.libcore.di.helper.CoreInjectHelper
import id.innovation.libcore.di.module.PresenterModule
import id.innovation.libcore.ui.controllers.BaseActivity
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.fhome.di.DaggerFeatureComponent


class HomeActivity : BaseActivity() {


    override fun injectDagger() {
        DaggerFeatureComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .presenterModule(PresenterModule(this))
            .build()
            .inject(this)
    }

    override fun getContentResource(): Int {
        return R.layout.activity_home
    }

}
