package id.rezkyauliapratama.fhome.data.mock

import id.innovation.libuicomponent.R
import id.rezkyauliapratama.fhome.data.entity.ListMoviesDto
import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean

class TvShowMockDataFactory {
    companion object {

        fun create(): ListMoviesDto =
            generateTvShows()

        private fun generateTvShows(): ListMoviesDto {
            return ListMoviesDto(moviesDto = generateTvShowsBean())
        }

        private fun generateTvShowsBean(): List<MovieDtoBean> {
            return arrayListOf(
                MovieDtoBean(
                    id = 0,
                    posterPath = R.drawable.poster_arrow,
                    title = "Arrow",
                    popularity = 2.5,
                    releaseDate = "October 10, 2012",
                    overview = "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow."
                ),
                MovieDtoBean(
                    id = 1,
                    posterPath = R.drawable.poster_doom_patrol,
                    title = "Doom Patrol",
                    popularity = 2.8,
                    releaseDate = "February 15, 2019",
                    overview = "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find."
                ),
                MovieDtoBean(
                    id = 2,
                    posterPath = R.drawable.poster_dragon_ball,
                    title = "Dragon Ball",
                    popularity = 4.0,
                    releaseDate = "February 26, 1986",
                    overview = "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the dragon balls brought her to Goku's home. Together, they set off to find all seven dragon balls in an adventure."
                ),
                MovieDtoBean(
                    id = 3,
                    posterPath = R.drawable.poster_fairytail,
                    title = "Fairy Tail",
                    popularity = 3.0,
                    releaseDate = "October 12, 2009",
                    overview = "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail."
                ),
                MovieDtoBean(
                    id = 4,
                    posterPath = R.drawable.poster_family_guy,
                    title = "Family Guy",
                    popularity = 3.0,
                    releaseDate = "January 31, 1999",
                    overview = "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues."
                ),
                MovieDtoBean(
                    id = 5,
                    posterPath = R.drawable.poster_flash,
                    title = "The Flash",
                    popularity = 3.3,
                    releaseDate = "October 7, 2014",
                    overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash."
                ),
                MovieDtoBean(
                    id = 6,
                    posterPath = R.drawable.poster_god,
                    title = "Game of Thrones",
                    popularity = 4.1,
                    releaseDate = "April 17, 2011",
                    overview = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
                ),
                MovieDtoBean(
                    id = 7,
                    posterPath = R.drawable.poster_gotham,
                    title = "Gotham",
                    popularity = 3.5,
                    releaseDate = "September 22, 2014",
                    overview = "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker? "
                ),
                MovieDtoBean(
                    id = 8,
                    posterPath = R.drawable.poster_grey_anatomy,
                    title = "Grey's Anatomy",
                    popularity = 3.1,
                    releaseDate = "March 27, 2005",
                    overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
                ),
                MovieDtoBean(
                    id = 9,
                    posterPath = R.drawable.poster_hanna,
                    title = "Hanna",
                    popularity = 3.5,
                    releaseDate = "March 28, 2019",
                    overview = "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film."
                )
            )
        }


    }
}