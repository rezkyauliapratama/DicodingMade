package id.rezkyauliapratama.fhome.ui.favorite.movie.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.innovation.libcore.ui.common.inflate
import id.innovation.libuicomponent.common.extension.loadImage
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import id.rezkyauliapratama.fhome.ui.popularmovie.adapter.entity.RowMovieResult
import kotlinx.android.synthetic.main.list_item_movie.view.*
import javax.inject.Inject

class FavoriteMovieAdapter @Inject constructor(
    private val rowMovieResult: RowMovieResult.Factory
) : RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder>() {

    lateinit var onClickItem: (Int) -> Unit

    val popularMovieResult: ArrayList<PopularMovieResult> = arrayListOf()

    fun setItems(popularMovieResult: List<PopularMovieResult>) {
        this.popularMovieResult.clear()
        if (popularMovieResult.isNotEmpty()) {
            this.popularMovieResult.addAll(popularMovieResult)
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
        return popularMovieResult.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        popularMovieResult[position].apply {
            val rowMovieResult = rowMovieResult.create(this)
            holder.bind(rowMovieResult, onClickItem)
        }
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

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
}