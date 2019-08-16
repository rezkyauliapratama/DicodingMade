package id.rezkyauliapratama.fhome.ui

import id.innovation.libcore.di.CoreInjectHelper.provideCoreComponent
import id.innovation.libcore.di.PresenterModule
import id.innovation.libcore.ui.controllers.BaseFragment
import id.rezkyauliapratama.fhome.R
import id.innovation.libuicomponent.R as R2
import id.rezkyauliapratama.fhome.di.DaggerFeatureComponent
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar.*
import id.rezkyauliapratama.fhome.ui.pager.HomePagerAdapter

class HomeFragment : BaseFragment() {

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
    }


}