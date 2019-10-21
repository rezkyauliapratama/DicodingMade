package id.rezkyauliapratama.fhome.ui

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import id.innovation.libcore.di.helper.CoreInjectHelper
import id.innovation.libcore.di.module.PresenterModule
import id.innovation.libcore.ui.common.SafeObserver
import id.innovation.libcore.ui.controllers.BaseViewModelActivity
import id.rezkyauliapratama.dicodingmade.service.AlarmService
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.fhome.di.DaggerFeatureComponent
import id.rezkyauliapratama.fhome.ui.common.RxSearchObservable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber
import java.util.concurrent.TimeUnit
import id.innovation.libuicomponent.R as R2


class HomeActivity : BaseViewModelActivity<HomeViewModel>() {

    private var isFirstTime = true
    private var searchView: SearchView? = null

    override fun buildViewModel(): HomeViewModel {
        return ViewModelProviders.of(this, mViewModelFactory)[HomeViewModel::class.java]
    }

    override fun injectDagger() {
        DaggerFeatureComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .presenterModule(PresenterModule(this))
            .build()
            .inject(this)
    }

    override fun initViews() {
        super.initViews()
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R2.string.home)
    }

    override fun initLiveDataObservers() {
        super.initLiveDataObservers()
        viewModel.changeStateLiveData.observe(this, SafeObserver(::changeStateResult))
    }

    private fun changeStateResult(visible: Int) {
        searchView?.setQuery("", true)
        searchView?.visibility = visible
    }

    override fun getContentResource(): Int {
        return R.layout.activity_home
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Timber.e("On Create Option")
        menuInflater.inflate(R.menu.menu_option, menu)
        val searchItem = menu.findItem(R.id.action_search)
        // Optional: if you want to expand SearchView from icon to edittext view
        searchItem.expandActionView()

        searchView = searchItem.actionView as SearchView

        RxSearchObservable.fromView(searchView)
            .debounce(1, TimeUnit.SECONDS)
            .filter {
                val tempBol = isFirstTime
                isFirstTime = false
                !tempBol
            }
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Timber.e("searchview : $it")
                viewModel.setQuery(it)
            }?.track()
        return true
    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager =
            getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}
