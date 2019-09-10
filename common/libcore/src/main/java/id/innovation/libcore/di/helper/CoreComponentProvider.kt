package id.innovation.libcore.di.helper

import id.innovation.libcore.di.CoreComponent

interface CoreComponentProvider {
    fun provideCoreComponent(): CoreComponent
}
