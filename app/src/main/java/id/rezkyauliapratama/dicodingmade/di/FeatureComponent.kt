package id.rezkyauliapratama.dicodingmade.di

import dagger.Component
import id.innovation.libcore.di.CoreComponent
import id.innovation.libcore.di.annotation.FeatureScope
import id.rezkyauliapratama.dicodingmade.service.AlarmBroadCastReceiver
import id.rezkyauliapratama.dicodingmade.widget.StackWidgetService


@Component(
    modules = [
        RepositoryModule::class
    ],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface FeatureComponent {
    fun inject(stackWidgetService: StackWidgetService)
    fun inject(alarmBroadcastReceiver: AlarmBroadCastReceiver)

    @Component.Builder
    interface Builder {
        fun build(): FeatureComponent
        fun coreComponent(component: CoreComponent): Builder
    }

}
