package id.innovation.libcore.ui.presenterstate

data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val throwable: Throwable? = null
)
