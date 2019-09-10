package id.rezkyauliapratama.fdetailmovie.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.innovation.libcore.di.annotation.ViewModelKey
import id.rezkyauliapratama.fdetailmovie.ui.DetailMovieViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailMovieViewModel::class)
    abstract fun bindDetailMovieViewModel(viewModel: DetailMovieViewModel): ViewModel
}