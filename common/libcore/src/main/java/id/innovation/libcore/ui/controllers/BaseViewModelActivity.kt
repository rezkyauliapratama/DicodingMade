package id.innovation.libcore.ui.controllers

import android.os.Bundle
import androidx.annotation.CallSuper
import id.innovation.libcore.ui.viewmodels.BaseViewModel

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


}
