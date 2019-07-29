package id.innovation.libcore.di

import id.innovation.libcore.di.CoreComponent

interface CoreComponentProvider {
    fun provideCoreComponent(): CoreComponent
}
