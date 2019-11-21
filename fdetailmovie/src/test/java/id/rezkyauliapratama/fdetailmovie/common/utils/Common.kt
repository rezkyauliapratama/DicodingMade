package id.rezkyauliapratama.fdetailmovie.common.utils

import com.nhaarman.mockitokotlin2.mock
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import id.innovation.libcore.domain.common.NetworkSchedulerTransformer
import id.innovation.libcore.domain.executors.PostExecutionThread
import id.innovation.libcore.domain.executors.PreExecutionThread
import id.innovation.libcore.errorhandler.ErrorTransformer
import id.innovation.libnetwork.common.NetworkErrorInterface
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response


object PostThreadExecutor : PostExecutionThread {
    override val scheduler: Scheduler
        get() = Schedulers.trampoline()

}


object PreThreadExecutor : PreExecutionThread {
    override val scheduler: Scheduler
        get() = Schedulers.trampoline()

}
val errorBody = mock<NetworkErrorInterface>()

val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

inline fun <reified T> errorTransformer(): ErrorTransformer<T> =
    ErrorTransformer(moshi, errorBody)

inline fun <reified T> networkScheduler(): NetworkSchedulerTransformer<T> =
        NetworkSchedulerTransformer(PreThreadExecutor, PostThreadExecutor)

inline fun <reified T> getSessionExpiredError(): HttpException {
    val mockUnauthorizeResponse = "{\"status\":\"error\",\"message\":{\"status\":\"-1200\",\"title\":\"Unauthorized\",\"detail\":\"Invalid auth token\"}}"
    val responseBody = ResponseBody.create(
            MediaType.get("text/plain; charset=utf-8"),
            mockUnauthorizeResponse
    )

    val response = Response.error<T>(401, responseBody)
    return HttpException(response)
}