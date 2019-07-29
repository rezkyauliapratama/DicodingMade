package id.innovation.libcore.ui.controllers

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.annotation.CallSuper
import id.innovation.libcore.ui.viewmodels.BaseViewModel
import java.net.ConnectException
import id.co.rezkyauliapratama.libcore.R
import id.innovation.libnetwork.common.NetworkException
import timber.log.Timber

abstract class BaseViewModelFragment<VIEWMODEL : BaseViewModel>
    : BaseFragment() {

    protected val viewModel by lazy { buildViewModel() }

    //to handle loading view in the page
    private val pageLoadingView by lazy { findPageLoadingView() }

    //to handle the view that contains content
    private val pageContentView by lazy { findPageContentView() }

    //to handle the error view in the page
    private val pageErrorView by lazy { findPageErrorView() }

    protected open fun findPageLoadingView(): View? = null

    protected open fun findPageContentView(): View? = null

    protected open fun findPageErrorView(): View? = null

    protected abstract fun buildViewModel(): VIEWMODEL

    protected open fun isMultipleLoad(): Boolean = false


    @CallSuper
    protected open fun initLiveDataObservers() = Unit

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLiveDataObservers()
    }

    override fun onStart() {
        super.onStart()
        Timber.e("Onstart")
        viewModel.loadPage(isMultipleLoad())
    }


    protected fun resetPageLoadState() {
        pageLoadingView?.isVisible = false
        pageContentView?.isVisible = true
        pageErrorView?.isVisible = false
    }

    @CallSuper
    protected open fun handlePageSuccess(pageContentView: View?) {
        pageLoadingView?.isVisible = false
        pageContentView?.isVisible = true
        pageErrorView?.isVisible = false
    }

    protected open fun handlePageLoading() {
        pageLoadingView?.isVisible = true
        pageContentView?.isVisible = false
        pageErrorView?.isVisible = false
    }

    @CallSuper
    protected open fun handlePageError(pageErrorView: View? = null) {
        pageLoadingView?.isVisible = false
        pageContentView?.isVisible = false
        pageErrorView?.isVisible = true
    }

    protected fun handleGenericError(throwable: Throwable, pageErrorView: View? = null) {
//        if (BuildConfig.DEBUG) throwable.printStackTrace()
        val errorMessage: String? = when (throwable) {
            is ConnectException -> if (pageErrorView != null) {
                handleNoConnectionPageError(pageErrorView)
                null
            } else {
                getString(R.string.general_no_internet_error_title)
            }
            is NetworkException.UnauthorizedException -> {
                null
            }
            else -> throwable.localizedMessage
        }

        //TODO Rezky, handle the behaviour to display error message
    }

    private fun handleNoConnectionPageError(pageErrorView: View) {
        handlePageError(pageErrorView)
        //pageErrorView.btnRetry.setOnClickListener { viewModel.loadPage(true) }
    }
}
