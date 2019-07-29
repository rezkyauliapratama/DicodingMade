package id.innovation.libcore.ui.controllers

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory


    private lateinit var compositeDisposable: CompositeDisposable

    protected abstract fun getContentResource(): Int

    protected abstract fun injectDagger()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            injectDagger()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        compositeDisposable = CompositeDisposable()
        return inflater.inflate(getContentResource(), container, false)
    }

    @CallSuper
    protected open fun initViews() = Unit

    override fun onDestroyView() {
        super.onDestroyView()
        dispose()
    }

    private fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    protected fun Disposable.collect() = compositeDisposable.add(this)

}
