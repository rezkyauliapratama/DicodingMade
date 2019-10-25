package id.rezkyauliapratama.dicodingmade.favoriteapp.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.innovation.libcore.di.annotation.ViewModelKey
import id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.FavoriteViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel

}