package id.rezkyauliapratama.fhome.data.mock

import android.content.Context
import id.innovation.libuicomponent.R
import id.rezkyauliapratama.fhome.data.entity.ListMoviesDto
import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean

class PopularMovieMockDataFactory {
    companion object {

        fun create(context: Context): ListMoviesDto =
            generateMovies(context)

        private fun generateMovies(context: Context): ListMoviesDto {
            return ListMoviesDto(moviesDto = generateMoviesBean(context))
        }

        private fun generateMoviesBean(context: Context): List<MovieDtoBean> {
            return arrayListOf(
                MovieDtoBean(
                    id = 0,
                    posterPath = R.drawable.poster_infinity_war,
                    title = "Avengers: Infinity War",
                    popularity = 4.3,
                    releaseDate = "April 27, 2018",
                    overview = context.getString(R.string.movie_avenger)
                ),
                MovieDtoBean(
                    id = 1,
                    posterPath = R.drawable.poster_spiderman,
                    title = "Spider-Man: Into the Spider-Verse",
                    popularity = 4.3,
                    releaseDate = "December 14, 2018",
                    overview = context.getString(R.string.movie_spiderman)
                ),
                MovieDtoBean(
                    id = 2,
                    posterPath = R.drawable.poster_a_start_is_born,
                    title = "A Star Is Born",
                    popularity = 3.3,
                    releaseDate = "October 5, 2018",
                    overview = context.getString(R.string.movie_star_is_born)
                ),
                MovieDtoBean(
                    id = 3,
                    posterPath = R.drawable.poster_mortal_engines,
                    title = "Mortal Engines",
                    popularity = 3.0,
                    releaseDate = "December 14, 2018",
                    overview = context.getString(R.string.movie_mortal_engines)
                ),
                MovieDtoBean(
                    id = 4,
                    posterPath = R.drawable.poster_aquaman,
                    title = "Aquaman",
                    popularity = 3.2,
                    releaseDate = "December 21, 2018",
                    overview = context.getString(R.string.movie_aquaman)
                ),
                MovieDtoBean(
                    id = 5,
                    posterPath = R.drawable.poster_bohemian,
                    title = "Bohemian Rhapsody",
                    popularity = 4.2,
                    releaseDate = "November 2, 2018",
                    overview = context.getString(R.string.movie_bohemian)
                ),
                MovieDtoBean(
                    id = 6,
                    posterPath = R.drawable.poster_alita,
                    title = "Alita: Battle Angel",
                    popularity = 4.6,
                    releaseDate = "February 14, 2019",
                    overview = context.getString(R.string.movie_alita)
                ),
                MovieDtoBean(
                    id = 7,
                    posterPath = R.drawable.poster_cold_persuit,
                    title = "Cold Pursuit",
                    popularity = 2.9,
                    releaseDate = "February 7, 2019",
                    overview = context.getString(R.string.movie_cold_persuit)
                ),
                MovieDtoBean(
                    id = 8,
                    posterPath = R.drawable.poster_glass,
                    title = "Glass",
                    popularity = 3.1,
                    releaseDate = "January 16, 2019",
                    overview = context.getString(R.string.movie_glass)
                ),
                MovieDtoBean(
                    id = 9,
                    posterPath = R.drawable.poster_how_to_train,
                    title = "How to Train Your Dragon 2",
                    popularity = 3.5,
                    releaseDate = "June 9, 2014",
                    overview = context.getString(R.string.movie_how_to_train)
                ),
                MovieDtoBean(
                    id = 5,
                    posterPath = R.drawable.poster_serenity,
                    title = "Serenity",
                    popularity = 4.2,
                    releaseDate = "January 24, 2019",
                    overview = context.getString(R.string.movie_serenity)
                )

            )
        }


    }
}