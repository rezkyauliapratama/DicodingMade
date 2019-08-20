package id.rezkyauliapratama.fhome.ui.pager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import id.rezkyauliapratama.fhome.ui.popularmovie.PopularMovieFragment
import id.rezkyauliapratama.fhome.ui.tvshow.TvShowFragment
import id.innovation.libuicomponent.R as R2

class HomePagerAdapter(
    private val context: Context,
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments by lazy {
        listOf(PopularMovieFragment(), TvShowFragment())
    }

    companion object {
        private const val MOVIE = 0
        private const val TVSHOW = 1
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            MOVIE -> context.getString(R2.string.home_tab_movie)
            TVSHOW -> context.getString(R2.string.home_tab_tv_show)
            else -> throw IndexOutOfBoundsException("Tab position is $position when the tabs count is $count")
        }
    }


}