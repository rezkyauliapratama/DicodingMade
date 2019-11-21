package id.rezkyauliapratama.fhome.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import id.rezkyauliapratama.fhome.common.utils.TestRulesListener
import id.rezkyauliapratama.fhome.common.utils.errorTransformer
import id.rezkyauliapratama.fhome.common.utils.networkScheduler
import id.rezkyauliapratama.fhome.domain.entity.mapToTvShowList
import id.rezkyauliapratama.fhome.domain.repository.MovieRepository
import id.rezkyauliapratama.fhome.domain.usecase.TvShowUsecase
import io.kotlintest.extensions.TestListener
import io.kotlintest.specs.BehaviorSpec
import io.reactivex.Single

class TvShowUseCaseUnitTest : BehaviorSpec() {
    private val movieRepository = mock<MovieRepository>()

    private val tvShowUsecae = TvShowUsecase(movieRepository).apply {
        networkSchedulerTransformer = networkScheduler()
        errorApiTransformer = errorTransformer()
    }

    override fun listeners(): List<TestListener> = listOf(TestRulesListener)

    init {
        buildUseCaseSingle_testCase()
    }
    private fun buildUseCaseSingle_testCase() {
        given("execute TvShowUseCase") {
            `when`("success to return list of tv show") {
                stubFetchTvShowModelsSuccess()
                then("The observer completes and emits tv show results response") {
                    tvShowUsecae.execute()
                        .test()
                        .await()
                        .assertSubscribed()
                        .assertValue {
                            it == movieModels.mapToTvShowList()
                        }
                        .assertComplete()
                        .assertNoErrors()
                }
            }
        }
    }

    // ------- STUBBING -------
    private fun stubFetchTvShowModelsSuccess() {
        whenever(
            movieRepository.getTvShows()
        ).thenReturn(
            Single.just(movieModels)
        )
    }
}