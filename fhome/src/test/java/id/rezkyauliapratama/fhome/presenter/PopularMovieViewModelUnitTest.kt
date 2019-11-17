package id.rezkyauliapratama.fhome.presenter

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.presenterstate.ResourceState
import id.rezkyauliapratama.fhome.common.utils.TestRulesListener
import id.rezkyauliapratama.fhome.common.utils.instantTaskExecutorRule
import id.rezkyauliapratama.fhome.domain.usecase.PopularMovieUsecase
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import id.rezkyauliapratama.fhome.ui.popularmovie.viewmodel.PopularMovieViewModel
import io.kotlintest.Spec
import io.kotlintest.TestResult
import io.kotlintest.extensions.TestListener
import io.kotlintest.matchers.instanceOf
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import io.reactivex.Single

class PopularMovieViewModelUnitTest : BehaviorSpec() {

    private val popularMovieUseCase = mock<PopularMovieUsecase>()
    private var popularMovieViewModel : PopularMovieViewModel = PopularMovieViewModel(popularMovieUseCase)
    private val moviesListObserver = mock<Observer<Resource<List<PopularMovieResult>>>>()
    override fun listeners(): List<TestListener> = listOf(TestRulesListener)

    init {
        loadPage_testCase()
    }

    override fun afterTest(testCase: io.kotlintest.TestCase, result: TestResult) {
        super.afterTest(testCase, result)
        reset(moviesListObserver)
        popularMovieViewModel = PopularMovieViewModel(popularMovieUseCase)
    }

    private fun loadPage_testCase() {
        Given("Calling loadPage method ") {
            `when`("successful") {
                stubFetchPopularMovieSuccess()
                popularMovieViewModel.moviesList.observeForever(moviesListObserver)
                popularMovieViewModel.loadPage()

                then("Showing the list of popular movie") {
                    argumentCaptor<Resource<List<PopularMovieResult>>> {
                        verify(moviesListObserver, times(2)).onChanged(capture())
                        firstValue.state shouldBe ResourceState.LOADING
                        secondValue.state shouldBe ResourceState.SUCCESS
                        secondValue.data shouldBe  popularMovieResults
                    }
                }
            }
            `when`("failed") {
                stubFetchPopularMovieFailure()
                popularMovieViewModel.moviesList.observeForever(moviesListObserver)
                popularMovieViewModel.loadPage()

                then("failure to fetch list of popular movie") {
                    argumentCaptor<Resource<List<PopularMovieResult>>> {
                        verify(moviesListObserver, times(2)).onChanged(capture())
                        firstValue.state shouldBe ResourceState.LOADING
                        secondValue.state shouldBe ResourceState.ERROR
                        secondValue.throwable should instanceOf(IllegalStateException::class)
                    }
                }
            }
        }
    }

    // output
    private val popularMovieResults = listOf<PopularMovieResult>(
        PopularMovieResult(
            id = 1,
            title = "title 1",
            popularity = 4.5,
            posterPath = 1,
            releaseDate = "2019-11-01",
            overview = "overview 1"
        ),
        PopularMovieResult(
            id = 2,
            title = "title 2",
            popularity = 4.5,
            posterPath = 2,
            releaseDate = "2019-11-02",
            overview = "overview 2"
        ),
        PopularMovieResult(
            id = 3,
            title = "title 3",
            popularity = 4.5,
            posterPath = 3,
            releaseDate = "2019-11-03",
            overview = "overview 3"
        ),
        PopularMovieResult(
            id = 4,
            title = "title 4",
            popularity = 4.5,
            posterPath = 4,
            releaseDate = "2019-11-04",
            overview = "overview 4"
        ),
        PopularMovieResult(
            id = 5,
            title = "title 5",
            popularity = 4.5,
            posterPath = 5,
            releaseDate = "2019-11-05",
            overview = "overview 5"
        )
    )

    // ------- STUBBING -------
    private fun stubFetchPopularMovieSuccess() {
        whenever(popularMovieUseCase.execute()).thenReturn(Single.just(popularMovieResults))
    }

    private fun stubFetchPopularMovieFailure() {
        whenever(
            popularMovieUseCase.execute()
        ).thenReturn(
            Single.error(IllegalStateException())
        )
    }
}