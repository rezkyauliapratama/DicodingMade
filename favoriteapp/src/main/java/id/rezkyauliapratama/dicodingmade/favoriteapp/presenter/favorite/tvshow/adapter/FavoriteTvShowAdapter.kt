package id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.favorite.tvshow.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.innovation.libcore.ui.common.inflate
import id.innovation.libuicomponent.common.extension.loadImage
import id.rezkyauliapratama.dicodingmade.favoriteapp.R
import id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.entity.RowTvShowResult
import id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.entity.TvShowResult
import kotlinx.android.synthetic.main.list_item_movie.view.*
import javax.inject.Inject

class FavoriteTvShowAdapter @Inject constructor(
    private val rowTvShowResult: RowTvShowResult.Factory
) : RecyclerView.Adapter<FavoriteTvShowAdapter.ViewHolder>() {

    lateinit var onClickItem: (Int) -> Unit

    val tvShows: ArrayList<TvShowResult> = arrayListOf()

    fun setItems(tvShows: List<TvShowResult>) {
        this.tvShows.clear()
        if (tvShows.isNotEmpty()) {
            this.tvShows.addAll(tvShows)
        }
        notifyDataSetChanged()
    }

    fun setOnClick(onClick: (Int) -> Unit) {
        this.onClickItem = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.inflate(R.layout.list_item_movie)
        )
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        tvShows[position].apply {
            val rowTvShowResult = rowTvShowResult.create(this)
            holder.bind(rowTvShowResult, onClickItem)
        }
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(moveResult: RowTvShowResult, onClick: (Int) -> Unit) {
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
}