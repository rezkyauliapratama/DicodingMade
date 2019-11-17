package id.rezkyauliapratama.fhome.presenter

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.presenterstate.ResourceState
import id.rezkyauliapratama.fhome.common.utils.TestRulesListener
import id.rezkyauliapratama.fhome.domain.usecase.TvShowUsecase
import id.rezkyauliapratama.fhome.ui.entity.TvShowResult
import id.rezkyauliapratama.fhome.ui.tvshow.viewmodel.TvShowViewModel
import io.kotlintest.TestResult
import io.kotlintest.extensions.TestListener
import io.kotlintest.matchers.instanceOf
import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import io.reactivex.Single

class TvShowViewModelUnitTest : BehaviorSpec() {

    private val tvShowUsecase = mock<TvShowUsecase>()
    private var tvShowViewModel = TvShowViewModel(tvShowUsecase)
    private val tvShowsObserver = mock<Observer<Resource<List<TvShowResult>>>>()
    override fun listeners(): List<TestListener> = listOf(TestRulesListener)

    init {
        loadPage_testCase()
    }

    override fun afterTest(testCase: io.kotlintest.TestCase, result: TestResult) {
        super.afterTest(testCase, result)
        reset(tvShowsObserver)
        tvShowViewModel = TvShowViewModel(tvShowUsecase)
    }

    private fun loadPage_testCase() {
        Given("Calling loadPage method") {
            `when`("successful") {
                stubFetchTvShowSuccess()
                tvShowViewModel.tvShowLiveData.observeForever(tvShowsObserver)
                tvShowViewModel.loadPage()

                then("Showing the list of tv show") {
                    argumentCaptor<Resource<List<TvShowResult>>> {
                        verify(tvShowsObserver, times(2)).onChanged(capture())
                        firstValue.state shouldBe ResourceState.LOADING
                        secondValue.state shouldBe ResourceState.SUCCESS
                        secondValue.data shouldBe tvShowResults
                    }
                }
            }

            `when`("failed") {
                stubFetchTvShowFailure()
                tvShowViewModel.tvShowLiveData.observeForever(tvShowsObserver)
                tvShowViewModel.loadPage()

                then("failure to fetch list of tv show") {
                    argumentCaptor<Resource<List<TvShowResult>>> {
                        verify(tvShowsObserver, times(2)).onChanged(capture())
                        firstValue.state shouldBe ResourceState.LOADING
                        secondValue.state shouldBe ResourceState.ERROR
                        secondValue.throwable shouldBe instanceOf(java.lang.IllegalStateException::class)
                    }
                }
            }
        }
    }

    // output
    private val tvShowResults = listOf(
        TvShowResult(
            id = 1,
            title = "title 1",
            popularity = 4.5,
            posterPath = 1,
            releaseDate = "2019-11-01",
            overview = "overview 1"
        ),
        TvShowResult(
            id = 2,
            title = "title 2",
            popularity = 4.5,
            posterPath = 2,
            releaseDate = "2019-11-02",
            overview = "overview 2"
        ),
        TvShowResult(
            id = 3,
            title = "title 3",
            popularity = 4.5,
            posterPath = 3,
            releaseDate = "2019-11-03",
            overview = "overview 3"
        ),
        TvShowResult(
            id = 4,
            title = "title 4",
            popularity = 4.5,
            posterPath = 4,
            releaseDate = "2019-11-04",
            overview = "overview 4"
        ),
        TvShowResult(
            id = 5,
            title = "title 5",
            popularity = 4.5,
            posterPath = 5,
            releaseDate = "2019-11-05",
            overview = "overview 5"
        )
    )

    // ------- STUBBING -------
    private fun stubFetchTvShowSuccess() {
        whenever(tvShowUsecase.execute()).thenReturn(Single.just(tvShowResults))
    }

    private fun stubFetchTvShowFailure() {
        whenever(
            tvShowUsecase.execute()
        ).thenReturn(
            Single.error(IllegalStateException())
        )
    }
}