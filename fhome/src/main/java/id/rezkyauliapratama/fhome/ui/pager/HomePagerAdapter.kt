package id.rezkyauliapratama.fhome.ui.pager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import id.rezkyauliapratama.fhome.ui.popularmovie.PopularMovieFragment
import id.rezkyauliapratama.fhome.ui.tvshow.TvShowFragment
import id.innovation.libuicomponent.R as R2

class HomePagerAdapter(
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }

}