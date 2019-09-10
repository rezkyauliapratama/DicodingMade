package id.rezkyauliapratama.fhome.ui

import id.innovation.libcore.di.helper.CoreInjectHelper.provideCoreComponent
import id.innovation.libcore.ui.controllers.BaseFragment
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.fhome.di.DaggerFeatureComponent
import id.rezkyauliapratama.fhome.ui.bottomsheet.SettingBottomSheetDialog
import id.rezkyauliapratama.fhome.ui.pager.HomePagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar.*
import id.innovation.libuicomponent.R as R2
import id.innovation.libcore.di.module.PresenterModule

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
        tvTitle.setText(R2.string.home)
        vpContainer.adapter = homePagerAdapter
        tabLayout.setupWithViewPager(vpContainer)

        fabSetting.setOnClickListener {
            showDialogFragment(settingDialogBottomSheet, HomeActivity::class.java.simpleName.toString())
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }


}