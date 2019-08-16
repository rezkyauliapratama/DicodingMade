package id.rezkyauliapratama.fhome.data.mock

import id.innovation.libuicomponent.R
import id.rezkyauliapratama.fhome.data.entity.ListMoviesDto
import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean

class MovieMockDataFactory {
    companion object {

        fun create(): ListMoviesDto =
            generateMovies()

        private fun generateMovies(): ListMoviesDto {
            return ListMoviesDto(moviesDto = generateMoviesBean())
        }

        private fun generateMoviesBean(): List<MovieDtoBean> {
            return arrayListOf(
                MovieDtoBean(
                    id = 0,
                    posterPath = R.drawable.poster_infinity_war,
                    title = "Avengers: Infinity War",
                    popularity = 4.3,
                    releaseDate = "April 27, 2018",
                    overview = "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain."
                ),
                MovieDtoBean(
                    id = 1,
                    posterPath = R.drawable.poster_spiderman,
                    title = "Spider-Man: Into the Spider-Verse",
                    popularity = 4.3,
                    releaseDate = "December 14, 2018",
                    overview = "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension."
                ),
                MovieDtoBean(
                    id = 2,
                    posterPath = R.drawable.poster_a_start_is_born,
                    title = "A Star Is Born",
                    popularity = 3.3,
                    releaseDate = "October 5, 2018",
                    overview = "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons."
                ),
                MovieDtoBean(
                    id = 3,
                    posterPath = R.drawable.poster_mortal_engines,
                    title = "Mortal Engines",
                    popularity = 3.0,
                    releaseDate = "December 14, 2018",
                    overview = "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever."
                ),
                MovieDtoBean(
                    id = 4,
                    posterPath = R.drawable.poster_aquaman,
                    title = "Aquaman",
                    popularity = 3.2,
                    releaseDate = "December 21, 2018",
                    overview = "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne."
                ),
                MovieDtoBean(
                    id = 5,
                    posterPath = R.drawable.poster_bohemian,
                    title = "Bohemian Rhapsody",
                    popularity = 4.2,
                    releaseDate = "November 2, 2018",
                    overview = "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess."
                ),
                MovieDtoBean(
                    id = 6,
                    posterPath = R.drawable.poster_alita,
                    title = "Alita: Battle Angel",
                    popularity = 4.6,
                    releaseDate = "February 14, 2019",
                    overview = "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past."
                ),
                MovieDtoBean(
                    id = 7,
                    posterPath = R.drawable.poster_cold_persuit,
                    title = "Cold Pursuit",
                    popularity = 2.9,
                    releaseDate = "February 7, 2019",
                    overview = "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution."
                ),
                MovieDtoBean(
                    id = 8,
                    posterPath = R.drawable.poster_glass,
                    title = "Glass",
                    popularity = 3.1,
                    releaseDate = "January 16, 2019",
                    overview = "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men."
                ),
                MovieDtoBean(
                    id = 9,
                    posterPath = R.drawable.poster_how_to_train,
                    title = "How to Train Your Dragon 2",
                    popularity = 3.5,
                    releaseDate = "June 9, 2014",
                    overview = "The thrilling second chapter of the epic How To Train Your Dragon trilogy brings back the fantastical world of Hiccup and Toothless five years later. While Astrid, Snotlout and the rest of the gang are challenging each other to dragon races (the island's new favorite contact sport), the now inseparable pair journey through the skies, charting unmapped territories and exploring new worlds. When one of their adventures leads to the discovery of a secret ice cave that is home to hundreds of new wild dragons and the mysterious Dragon Rider, the two friends find themselves at the center of a battle to protect the peace."
                ),
                MovieDtoBean(
                    id = 5,
                    posterPath = R.drawable.poster_serenity,
                    title = "Serenity",
                    popularity = 4.2,
                    releaseDate = "January 24, 2019",
                    overview = "Baker Dill is a fishing boat captain leading tours off a tranquil, tropical enclave called Plymouth Island. His quiet life is shattered, however, when his ex-wife Karen tracks him down with a desperate plea for help."
                )

            )
        }


    }
}