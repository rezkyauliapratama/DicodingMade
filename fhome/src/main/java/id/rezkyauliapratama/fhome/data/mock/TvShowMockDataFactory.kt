package id.rezkyauliapratama.fhome.data.mock

import android.content.Context
import id.innovation.libuicomponent.R
import id.rezkyauliapratama.fhome.data.entity.ListMoviesDto
import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean

class TvShowMockDataFactory {
    companion object {

        fun create(context: Context): ListMoviesDto =
            generateTvShows(context)

        private fun generateTvShows(context: Context): ListMoviesDto {
            return ListMoviesDto(moviesDto = generateTvShowsBean(context))
        }

        private fun generateTvShowsBean(context: Context): List<MovieDtoBean> {
            return arrayListOf(
                MovieDtoBean(
                    id = 0,
                    posterPath = R.drawable.poster_arrow,
                    title = "Arrow",
                    popularity = 2.5,
                    releaseDate = "October 10, 2012",
                    overview = context.getString(R.string.tv_arrow)
                ),
                MovieDtoBean(
                    id = 1,
                    posterPath = R.drawable.poster_doom_patrol,
                    title = "Doom Patrol",
                    popularity = 2.8,
                    releaseDate = "February 15, 2019",
                    overview = context.getString(R.string.tv_doom_patrol)
                ),
                MovieDtoBean(
                    id = 2,
                    posterPath = R.drawable.poster_dragon_ball,
                    title = "Dragon Ball",
                    popularity = 4.0,
                    releaseDate = "February 26, 1986",
                    overview = context.getString(R.string.tv_dragon_ball)
                ),
                MovieDtoBean(
                    id = 3,
                    posterPath = R.drawable.poster_fairytail,
                    title = "Fairy Tail",
                    popularity = 3.0,
                    releaseDate = "October 12, 2009",
                    overview = context.getString(R.string.tv_fairy_Tail)
                ),
                MovieDtoBean(
                    id = 4,
                    posterPath = R.drawable.poster_family_guy,
                    title = "Family Guy",
                    popularity = 3.0,
                    releaseDate = "January 31, 1999",
                    overview = context.getString(R.string.tv_family_guy)
                ),
                MovieDtoBean(
                    id = 5,
                    posterPath = R.drawable.poster_flash,
                    title = "The Flash",
                    popularity = 3.3,
                    releaseDate = "October 7, 2014",
                    overview = context.getString(R.string.tv_flash)
                ),
                MovieDtoBean(
                    id = 6,
                    posterPath = R.drawable.poster_god,
                    title = "Game of Thrones",
                    popularity = 4.1,
                    releaseDate = "April 17, 2011",
                    overview = context.getString(R.string.tv_god)
                ),
                MovieDtoBean(
                    id = 7,
                    posterPath = R.drawable.poster_gotham,
                    title = "Gotham",
                    popularity = 3.5,
                    releaseDate = "September 22, 2014",
                    overview = context.getString(R.string.tv_gotham)
                ),
                MovieDtoBean(
                    id = 8,
                    posterPath = R.drawable.poster_grey_anatomy,
                    title = "Grey's Anatomy",
                    popularity = 3.1,
                    releaseDate = "March 27, 2005",
                    overview = context.getString(R.string.tv_grey_anatomy)
                ),
                MovieDtoBean(
                    id = 9,
                    posterPath = R.drawable.poster_hanna,
                    title = "Hanna",
                    popularity = 3.5,
                    releaseDate = "March 28, 2019",
                    overview = context.getString(R.string.tv_hanna)
                )
            )
        }


    }
}