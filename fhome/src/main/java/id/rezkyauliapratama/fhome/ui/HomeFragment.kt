package id.rezkyauliapratama.fhome.ui

import id.innovation.libcore.di.CoreInjectHelper.provideCoreComponent
import id.innovation.libcore.di.PresenterModule
import id.innovation.libcore.ui.controllers.BaseFragment
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.fhome.di.DaggerFeatureComponent
import id.rezkyauliapratama.fhome.ui.bottomsheet.SettingBottomSheetDialog
import id.rezkyauliapratama.fhome.ui.pager.HomePagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber
import id.innovation.libuicomponent.R as R2

class HomeFragment : BaseFragment() {

    private val settingDialogBottomSheet by lazy {
        SettingBottomSheetDialog()
    }

    private val homePagerAdapter by lazy {
        HomePagerAdapter(requireContext(), requireFragmentManager())
    }

    override fun getContentResource(): Int {
        return R.layout.fragment_home
    }

    override fun injectDagger() {
        DaggerFeatureComponent
            .builder()
            .coreComponent(provideCoreComponent(requireActivity().applicationContext))
            .presenterModule(PresenterModule(requireActivity()))
            .build()
            .inject(this)
    }

    override fun initViews() {
        super.initViews()
        Timber.e("initview")
        tvTitle.setText(R2.string.home)
        vpContainer.adapter = homePagerAdapter
        tabLayout.setupWithViewPager(vpContainer)

        fabSetting.setOnClickListener {
            showDialogFragment(settingDialogBottomSheet, HomeActivity::class.java.simpleName.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.e("onstart")
    }

    override fun onStop() {
        super.onStop()
        Timber.e("onstop")
    }


}