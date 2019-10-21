package id.rezkyauliapratama.fhome.ui.popularmovie.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.innovation.libcore.ui.common.inflate
import id.innovation.libcore.ui.presenterstate.ResourceState
import id.innovation.libuicomponent.common.extension.loadImage
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.dicodingmade.presenter.entity.PopularMovieResult
import id.rezkyauliapratama.fhome.ui.popularmovie.adapter.entity.RowMovieResult
import kotlinx.android.synthetic.main.list_item_movie.view.*
import kotlinx.android.synthetic.main.widget_progressbar.view.*
import timber.log.Timber
import javax.inject.Inject
import id.innovation.libuicomponent.R as R2

class PopularMovieAdapter @Inject constructor(
    private val rowMovieResultFactory: RowMovieResult.Factory
) : PagedListAdapter<PopularMovieResult, RecyclerView.ViewHolder>(popularMoviesDiffCallback) {

    lateinit var onClickItem: (Int) -> Unit

    companion object {

        const val DATA_VIEW_TYPE = 1
        const val FOOTER_VIEW_TYPE = 2

        val popularMoviesDiffCallback = object : DiffUtil.ItemCallback<PopularMovieResult>() {
            override fun areItemsTheSame(
                oldItem: PopularMovieResult,
                newItem: PopularMovieResult
            ): Boolean {
                return oldItem.originalTitle == newItem.originalTitle
            }

            override fun areContentsTheSame(
                oldItem: PopularMovieResult,
                newItem: PopularMovieResult
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setOnClick(onClick: (Int) -> Unit) {
        this.onClickItem = onClick
    }

    private var resourceState: ResourceState = ResourceState.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == DATA_VIEW_TYPE)
            PopularMovieViewHolder(
                parent.inflate(R.layout.list_item_movie)
            )
        else LoaderViewHolder(
            parent.inflate(R.layout.widget_progressbar)
        )
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0
                && (resourceState == ResourceState.LOADING
                || resourceState == ResourceState.ERROR)
    }

    fun setResourceState(resourceState: ResourceState) {
        this.resourceState = resourceState
        notifyItemChanged(super.getItemCount())
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE && holder is PopularMovieViewHolder) {
            getItem(position)?.apply {
                val rowPopularMovieResult = rowMovieResultFactory.create(this)
                holder.bind(rowPopularMovieResult, onClickItem)
            }
        } else
            (holder as LoaderViewHolder).bind(resourceState)
    }


    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

}

class PopularMovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(moveResult: RowMovieResult, onClick: (Int) -> Unit) {
        view.shimmer_view_container.startShimmer()
        view.tvTitle.text = moveResult.getOriginalTitle()
        view.tvScore.text = moveResult.getVoteAverage()
        view.ivPoster.loadImage(moveResult.getThumnailImage(),
            onLoad = {
                view.shimmer_view_container.startShimmer()
            },
            onSuccess = {
                view.shimmer_view_container.stopShimmer()
            }
        )

        view.setOnClickListener {
            onClick.invoke(moveResult.getId())
        }


    }
}

class LoaderViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {

    fun bind(itemData: ResourceState?) {
        if (itemData == ResourceState.LOADING) {
            view.pbFooter.show()
            view.tvFooterMessage.visibility = View.GONE
        } else {
            view.pbFooter.hide()
            view.tvFooterMessage.visibility = View.VISIBLE
            view.tvFooterMessage.text = view.context.getString(R2.string.footer_message)
        }
    }
}
