package id.rezkyauliapratama.fdetailmovie.di

import dagger.Component
import id.innovation.libcore.di.CoreComponent
import id.innovation.libcore.di.FeatureScope
import id.innovation.libcore.di.PresenterModule
import id.innovation.libcore.di.ViewModelFactoryModule
import id.rezkyauliapratama.fdetailmovie.ui.DetailMovieActivity
import id.rezkyauliapratama.fdetailmovie.ui.DetailMovieFragment


@Component(
    modules = [
        PresenterModule::class,
        ViewModelFactoryModule::class, ViewModelModule::class,
        RepositoryModule::class
    ],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface FeatureComponent {
    fun inject(detailMovieActivity: DetailMovieActivity)
    fun inject(detailMovieActivity: DetailMovieFragment)

    @Component.Builder
    interface Builder {
        fun build(): FeatureComponent
        fun coreComponent(component: CoreComponent): Builder
        fun presenterModule(module: PresenterModule): Builder
    }

}
