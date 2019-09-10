package id.rezkyauliapratama.fhome.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.innovation.libcore.di.annotation.ViewModelKey
import id.rezkyauliapratama.fhome.ui.popularmovie.viewmodel.PopularMovieViewModel
import id.rezkyauliapratama.fhome.ui.tvshow.viewmodel.TvShowViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularMovieViewModel::class)
    abstract fun bindPopularViewModel(viewModel: PopularMovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvShowViewModel::class)
    abstract fun bindTvShowViewModel(viewModel: TvShowViewModel): ViewModel

}