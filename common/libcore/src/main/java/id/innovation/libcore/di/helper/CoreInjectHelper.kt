package id.innovation.libcore.di.helper

import android.content.Context
import id.innovation.libcore.di.CoreComponent

object CoreInjectHelper {
    fun provideCoreComponent(applicationContext: Context): CoreComponent {
        return if (applicationContext is CoreComponentProvider) {
            (applicationContext as CoreComponentProvider).provideCoreComponent()
        } else {
            throw IllegalStateException(
                "The application context you have passed does not implement CoreComponentProvider"
            )
        }
    }
}
