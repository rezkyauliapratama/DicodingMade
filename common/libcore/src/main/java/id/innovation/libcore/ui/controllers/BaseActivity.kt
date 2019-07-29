package id.innovation.libcore.ui.controllers

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseActivity :
    AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun injectDagger()
    protected abstract fun getContentResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDagger()
        initViews()

        super.onCreate(savedInstanceState)
        setContentView(getContentResource())
    }

    @CallSuper
    protected open fun initViews() = Unit
}
