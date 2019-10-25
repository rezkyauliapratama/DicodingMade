package id.rezkyauliapratama.dicodingmade.favoriteapp.di

import dagger.Component
import id.innovation.libcore.di.CoreComponent
import id.innovation.libcore.di.annotation.FeatureScope
import id.innovation.libcore.di.module.PresenterModule
import id.innovation.libcore.di.module.ViewModelFactoryModule
import id.rezkyauliapratama.dicodingmade.favoriteapp.MainActivity
import id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.favorite.FavoriteFragment
import id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.favorite.movie.FavoriteMovieFragment
import id.rezkyauliapratama.dicodingmade.favoriteapp.presenter.favorite.tvshow.FavoriteTvShowFragment


@Component(
    modules = [
        PresenterModule::class,
        ViewModelFactoryModule::class, ViewModelModule::class, RepositoryModule::class
    ],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface FeatureComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: FavoriteMovieFragment)
    fun inject(favoriteTvShowFragment: FavoriteTvShowFragment)
    fun inject(favoriteFragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun build(): FeatureComponent
        fun coreComponent(component: CoreComponent): Builder
        fun presenterModule(module: PresenterModule): Builder
    }

}
