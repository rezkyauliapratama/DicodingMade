package id.rezkyauliapratama.dicodingmade.widget

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.os.Binder
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import id.innovation.libcore.utils.KeyUtils
import id.rezkyauliapratama.dicodingmade.BuildConfig
import id.rezkyauliapratama.dicodingmade.R
import id.rezkyauliapratama.dicodingmade.domain.entity.mapToPopularMovieList
import id.rezkyauliapratama.dicodingmade.domain.repository.MovieRepository
import id.rezkyauliapratama.dicodingmade.presenter.entity.PopularMovieResult
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber


class StackRemoteViewsFactory(
    private val context: Context,
    private val movieRepository: MovieRepository
) : RemoteViewsService.RemoteViewsFactory {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onDataSetChanged() {
        Timber.e("ondatasetchanged")
        val identityToken = Binder.clearCallingIdentity()

        compositeDisposable.add(
            movieRepository.getMovieFavorites().map {
                it.mapToPopularMovieList()
            }
                .subscribe(
                    {
                        mWidgetItems.clear()
                        it.map {
                            val movieResult = WidgetMovieResult(it)
                            mWidgetItems.add(movieResult.getThumnailImage())
                        }
                        Binder.restoreCallingIdentity(identityToken)
                    },
                    {
                        Binder.restoreCallingIdentity(identityToken)
                    }
                )
        )


    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(i: Int): Long = 0

    override fun hasStableIds(): Boolean = false

    private val mWidgetItems = ArrayList<String>()

    override fun onCreate() {
        LocalBroadcastManager.getInstance(context)
            .registerReceiver(
                updateWidgetBroadcast,
                IntentFilter(KeyUtils.BROADCAST_FAVORITE_WIDGET)
            )
    }

    override fun getCount(): Int = mWidgetItems.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(context.packageName, R.layout.widget_item)
        try {
            val bitmap: Bitmap = Glide.with(context)
                .asBitmap()
                .load(mWidgetItems[position])
                .submit(512, 512)
                .get()
            rv.setImageViewBitmap(R.id.imageView, bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val extras = bundleOf(
            FavoritesWidget.EXTRA_ITEM to position
        )
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)
        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
        return rv
    }

    private val updateWidgetBroadcast = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Timber.e("updateWidgetBroadcast ")
            onDataSetChanged()
        }
    }

    private fun unsubscribeBroadcast() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(updateWidgetBroadcast)
    }
}


class WidgetMovieResult(private val popularMovieResult: PopularMovieResult) {
    fun getId() = popularMovieResult.id
    fun getOriginalTitle() = popularMovieResult.originalTitle
    fun getVoteAverage() = popularMovieResult.popularity.toString()
    fun getThumnailImage() = StringBuilder().append(BuildConfig.IMAGE_BASE_URL)
        .append(popularMovieResult.posterPath).toString()

}
