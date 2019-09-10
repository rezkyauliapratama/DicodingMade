package id.innovation.libcore.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import id.innovation.libcore.di.DaggerViewModelFactory

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}
