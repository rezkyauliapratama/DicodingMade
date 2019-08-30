package id.innovation.libuicomponent.common.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


fun ImageView.loadImage(
    source: String?,
    requestOptions: RequestOptions? = null,
    onLoad: () -> Unit,
    onSuccess: (Boolean) -> Unit
) {
    onLoad.invoke()
    val requestBuilder = Glide.with(this.context)
        .load(source ?: "")
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onSuccess.invoke(false)
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onSuccess.invoke(true)
                return false
            }
        })

    requestOptions?.let {
        requestBuilder.apply(requestOptions)
    }
    requestBuilder.into(
        this
    )
}
