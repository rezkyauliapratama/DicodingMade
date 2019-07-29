package id.rezkyauliapratama.fhome.ui.popularmovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.rezkyauliapratama.fhome.R
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import kotlinx.android.synthetic.main.list_item_movie.view.*

class PopularMovieAdapter: RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {

    private val items: ArrayList<PopularMovieResult> = ArrayList()

    fun bind(items: List<PopularMovieResult>) {
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
        holder.bindMovies(items[position])
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindMovies(popularMovieResult: PopularMovieResult) {
            view.tvTitle.text = popularMovieResult.title
            view.tvScore.text = popularMovieResult.popularity.toString()
            view.ivPoster.setImageResource(popularMovieResult.posterPath)

        }
    }

}
