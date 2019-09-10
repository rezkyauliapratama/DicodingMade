package id.rezkyauliapratama.fdetailmovie.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.innovation.libdatabase.entity.FavoriteTable
import id.rezkyauliapratama.fdetailmovie.domain.repository.DetailContentRepository
import io.reactivex.Single
import javax.inject.Inject


class GetFavoriteUseCase @Inject constructor(
    private val detailContentRepository: DetailContentRepository,
    errorTransformer: ErrorTransformer<FavoriteTable>
) : SingleUseCase<FavoriteTable>(errorTransformer) {

    companion object {
        const val movieId: String = "movieId"
    }

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<FavoriteTable> {
        val movieId: Int = data[movieId] as Int
        return detailContentRepository.getFavorite(movieId.toString())
    }
}

