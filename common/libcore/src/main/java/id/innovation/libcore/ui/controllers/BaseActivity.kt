package id.innovation.libcore.ui.controllers

import android.content.Context
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import id.innovation.libcore.data.locale.LocaleManager



abstract class BaseActivity :
    AppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    abstract fun injectDagger()
    protected abstract fun getContentResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDagger()
        super.onCreate(savedInstanceState)
        setContentView(getContentResource())
        initViews()
    }

    @CallSuper
    protected open fun initViews() = Unit


    protected fun showDialogFragment(dialogFragment: DialogFragment, tag: String) {
        if (!dialogFragment.isAdded) {
            dialogFragment.show(supportFragmentManager, tag)
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.setLocale(newBase))
    }
}
