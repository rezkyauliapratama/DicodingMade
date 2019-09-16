package id.rezkyauliapratama.fhome.di

import dagger.Component
import id.innovation.libcore.di.CoreComponent
import id.innovation.libcore.di.annotation.FeatureScope
import id.innovation.libcore.di.module.PresenterModule
import id.innovation.libcore.di.module.ViewModelFactoryModule
import id.rezkyauliapratama.fhome.ui.HomeActivity
import id.rezkyauliapratama.fhome.ui.HomeFragment
import id.rezkyauliapratama.fhome.ui.bottomsheet.SettingBottomSheetDialog
import id.rezkyauliapratama.fhome.ui.favorite.FavoriteFragment
import id.rezkyauliapratama.fhome.ui.favorite.tvshow.FavoriteTvShowFragment
import id.rezkyauliapratama.fhome.ui.popularmovie.PopularMovieFragment
import id.rezkyauliapratama.fhome.ui.tvshow.TvShowFragment


@Component(
    modules = [
        PresenterModule::class, RepositoryModule::class,
        ViewModelFactoryModule::class, ViewModelModule::class
    ],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface FeatureComponent {

    @Component.Builder
    interface Builder {
        fun build(): FeatureComponent
        fun coreComponent(component: CoreComponent): Builder
        fun presenterModule(module: PresenterModule): Builder
    }

    fun inject(homeActivity: HomeActivity)
    fun inject(popularMovieFragment: PopularMovieFragment)
    fun inject(homeFragment: HomeFragment)
    fun inject(tvShowFragment: TvShowFragment)
    fun inject(settingBottomSheetDialog: SettingBottomSheetDialog)
    fun inject(favoriteFragment: FavoriteFragment)
    fun inject(favoriteTvShowFragment: FavoriteTvShowFragment)

}
