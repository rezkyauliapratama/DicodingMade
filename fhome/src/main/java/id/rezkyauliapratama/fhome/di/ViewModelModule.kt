package id.rezkyauliapratama.fhome.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.innovation.libcore.di.ViewModelKey
import id.rezkyauliapratama.fhome.ui.popularmovie.viewmodel.PopularMovieViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularMovieViewModel::class)
    abstract fun bindPopularViewModel(viewModel: PopularMovieViewModel): ViewModel

}