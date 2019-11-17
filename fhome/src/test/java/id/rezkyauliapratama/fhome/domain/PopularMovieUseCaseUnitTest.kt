package id.rezkyauliapratama.fhome.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.rezkyauliapratama.fhome.common.utils.TestRulesListener
import id.rezkyauliapratama.fhome.common.utils.errorTransformer
import id.rezkyauliapratama.fhome.common.utils.networkScheduler
import id.rezkyauliapratama.fhome.domain.entity.mapToPopularMovieList
import id.rezkyauliapratama.fhome.domain.repository.MovieRepository
import id.rezkyauliapratama.fhome.domain.usecase.PopularMovieUsecase
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import io.kotlintest.extensions.TestListener
import io.kotlintest.specs.BehaviorSpec
import io.reactivex.Single

class PopularMovieUseCaseUnitTest : BehaviorSpec() {

    private val movieRepository = mock<MovieRepository>()

    private val popularMovieUsecase = PopularMovieUsecase(movieRepository).apply {
        networkSchedulerTransformer = networkScheduler()
        errorApiTransformer = errorTransformer()
    }

    override fun listeners(): List<TestListener> = listOf(TestRulesListener)

    init {
        buildUseCaseSingle_testCase()
    }

    private fun buildUseCaseSingle_testCase() {
        given("execute PopularMovieUseCase") {
            `when`("success to return list of popular movie") {
                stubFetchMovieModelsSuccess()
                then("The observer completes and emits movie results response") {
                    popularMovieUsecase.execute()
                        .test()
                        .await()
                        .assertSubscribed()
                        .assertValue {
                            it == movieModels.mapToPopularMovieList()
                        }
                        .assertComplete()
                        .assertNoErrors()
                }
            }
        }
    }

    // ------- STUBBING -------
    private fun stubFetchMovieModelsSuccess() {
        whenever(
            movieRepository.getPopularMovies()
        ).thenReturn(
            Single.just(movieModels)
        )
    }
}