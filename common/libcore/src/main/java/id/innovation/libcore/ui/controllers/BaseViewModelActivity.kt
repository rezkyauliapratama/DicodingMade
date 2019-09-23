package id.innovation.libcore.ui.controllers

import android.os.Bundle
import androidx.annotation.CallSuper
import id.innovation.libcore.ui.viewmodels.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModelActivity<VIEWMODEL : BaseViewModel>
    : BaseActivity() {


    protected val viewModel by lazy { buildViewModel() }

    protected abstract fun buildViewModel(): VIEWMODEL


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLiveDataObservers()
        viewModel.loadPage()
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadPage()
    }

    @CallSuper
    protected open fun initLiveDataObservers() = Unit

    val compositeDisposable = CompositeDisposable()

    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }

}
