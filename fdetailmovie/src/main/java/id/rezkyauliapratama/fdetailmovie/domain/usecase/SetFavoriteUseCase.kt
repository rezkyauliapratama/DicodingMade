package id.rezkyauliapratama.fdetailmovie.domain.usecase

import id.innovation.libcore.domain.interactors.reactivebase.SingleUseCase
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.innovation.libdatabase.entity.FavoriteType
import id.rezkyauliapratama.fdetailmovie.domain.repository.DetailContentRepository
import io.reactivex.Single
import javax.inject.Inject

class SetFavoriteUseCase @Inject constructor(
    private val detailContentRepository: DetailContentRepository,
    errorTransformer: ErrorTransformer<Boolean>
) : SingleUseCase<Boolean>(errorTransformer) {

    companion object {
        const val itemId: String = "itemId"
        const val itemType: String = "itemType"
    }

    override fun buildUseCaseSingle(data: Map<String, Any?>): Single<Boolean> {
        val itemId: Int = data[itemId] as Int
        val itemType: FavoriteType = data[itemType] as FavoriteType
        return detailContentRepository.setFavorite(itemId.toString(), itemType)
    }
}

