package id.rezkyauliapratama.fdetailmovie.presenter

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import id.innovation.libsharedata.entity.DetailMovieResult
import id.rezkyauliapratama.fdetailmovie.common.utils.TestRulesListener
import id.rezkyauliapratama.fdetailmovie.ui.DetailMovieViewModel
import io.kotlintest.extensions.TestListener
import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec

class DetailMovieViewModelUnitTest : BehaviorSpec() {

    private val detailMovieViewModel = DetailMovieViewModel()
    private val detailMovieObserver = mock<Observer<DetailMovieResult>>()

    override fun listeners(): List<TestListener> = listOf(TestRulesListener)

    init {
        getDetailMovieResult_testCase()
    }

    private fun getDetailMovieResult_testCase() {
        Given("Calling getDetailMovieResult") {
            detailMovieViewModel.detailMovieLiveData.observeForever(detailMovieObserver)
            detailMovieViewModel.getDetailMovieResult(detailMovieResult)
            `when`("successful") {
                then("display the detail movie") {
                    argumentCaptor<DetailMovieResult> {
                        verify(detailMovieObserver, times(1)).onChanged(capture())
                        firstValue shouldBe detailMovieResult
                    }
                }
            }
        }
    }


    // output
    private val detailMovieResult = DetailMovieResult(
        id = 1,
        title = "title 1",
        popularity = 4.5,
        posterPath = 1,
        releaseDate = "2019-11-01",
        overview = "overview 1"
    )

}