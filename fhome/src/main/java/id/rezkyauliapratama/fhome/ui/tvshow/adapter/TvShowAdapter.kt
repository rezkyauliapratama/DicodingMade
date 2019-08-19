package id.rezkyauliapratama.fhome.ui.tvshow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.fhome.ui.entity.TvShowResult
import kotlinx.android.synthetic.main.list_item_movie.view.*

class TvShowAdapter(
    private val onClick: (tvShowResult: TvShowResult) -> Unit
) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private val items: ArrayList<TvShowResult> = ArrayList()

    fun bind(items: List<TvShowResult>) {
        this.items.clear()
        if (items.isNotEmpty()) {
            this.items.addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMovies(items[position], onClick)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindMovies(
            tvShowResult: TvShowResult,
            onClick: (tvShowResult: TvShowResult) -> Unit
        ) {
            view.tvTitle.text = tvShowResult.title
            view.tvScore.text = tvShowResult.popularity.toString()
            view.ivPoster.setImageResource(tvShowResult.posterPath)

            view.setOnClickListener {
                onClick.invoke(tvShowResult)
            }
        }
    }

}
